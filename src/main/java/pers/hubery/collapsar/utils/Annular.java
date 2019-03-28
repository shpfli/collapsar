/*
 * Copyright (c) 2019. Hubery
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
package pers.hubery.collapsar.utils;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 环
 * <p>首尾相连的链表组成，指针只能单向移动</p>
 *
 * @param <T> the type parameter
 * @author hubery
 */
public class Annular<T> implements Iterable<T> {

    /**
     * The number of times this Annular has been <i>structurally modified</i>.
     * Structural modifications are those that change the length of the
     * Annular, or otherwise perturb it in such a fashion that iterations in
     * progress may yield incorrect results.
     */
    private transient int modCount = 0;

    /** 指针，当前位置 */
    private AnnularNode<T> cursor;

    /** 上一位置 */
    private AnnularNode<T> previous;

    /** 环的长度 */
    private int length = 0;

    /**
     * Instantiates a new Annular.
     */
    public Annular() {
    }

    /**
     * Instantiates a new Annular.
     *
     * @param collection the collection
     */
    public Annular(Collection<T> collection) {

        for (T item : collection) {
            addNode(item);
        }
    }

    /**
     * 返回当前元素，并移到下一节点
     *
     * @return 当前元素 t
     */
    public T next() {
        T obj = this.cursor.obj;
        this.previous = this.cursor;
        this.cursor = this.cursor.next;

        return obj;
    }

    /**
     * 返回当前元素，指针不动
     *
     * @return 当前元素 t
     */
    public T peek() {
        return this.cursor.obj;
    }

    /**
     * 获取当前环的长度
     *
     * @return 当前环的长度 length
     */
    public int getLength() {
        return length;
    }

    /**
     * Is empty .
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * 添加一个对象
     *
     * @param obj the obj
     */
    public final void addNode(T obj) {

        if (cursor == null) {
            // 一个元素时，指向自身
            this.cursor = new AnnularNode<T>();
            this.cursor.obj = obj;
            this.cursor.next = this.cursor;

            //只有一个元素时，上一元素也指向自身
            this.previous = this.cursor;
            this.length = 1;
        } else {

            //新增一个节点，插进当前位置的上一位置
            AnnularNode<T> annularNode = new AnnularNode<T>();
            annularNode.obj = obj;
            annularNode.next = this.cursor;

            this.previous.next = annularNode;
            this.previous = annularNode;
            this.length++;
        }
    }

    /**
     * 移除并返回当前节点，指针将移到下一节点
     *
     * @return 被移除的节点对象 t
     */
    public T remove() {

        //要返回的结果
        final T result = this.cursor.obj;

        if (this.cursor == this.previous) {
            this.cursor = null;
            this.previous = null;
            this.length = 0;
        } else {
            //移除当前节点
            this.cursor = this.cursor.next;
            this.previous.next = this.cursor;
            this.length--;
        }

        return result;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder("Annular[");

        for (T node : this) {
            stringBuilder.append(node.toString()).append(",");
        }

        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<T> iterator() {

        return new AnnularItr();
    }

    /**
     * 环节点
     *
     * @param <T> the type parameter
     */
    private class AnnularNode<T> {

        /**
         * The Obj.
         */
        T obj;

        /**
         * The Next.
         */
        AnnularNode<T> next;

        /**
         * @see Object#toString()
         */
        @Override
        public String toString() {
            return obj.toString();
        }
    }

    /**
     * 环遍历器
     */
    private class AnnularItr implements Iterator<T> {

        /** 开始节点，用于判断是否已经结束 */
        final AnnularNode<T> startNode = cursor;

        /** 游标 */
        AnnularNode<T> cur = cursor;

        /**
         * The modCount value that the iterator believes that the backing
         * List should have.  If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        int expectedModCount = modCount;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.cur != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {

            checkForComodification();

            if (this.cur == null) {
                throw new NoSuchElementException();
            }

            T next = this.cur.obj;

            if (this.cur.next == this.startNode) {
                //说明已经结束了
                this.cur = null;
            } else {
                this.cur = this.cur.next;
            }

            return next;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.  The behavior of an iterator
         * is unspecified if the underlying collection is modified while the
         * iteration is in progress in any way other than by calling this
         * method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         * operation is not supported by this iterator
         * @throws IllegalStateException if the {@code next} method has not
         * yet been called, or the {@code remove} method has already
         * been called after the last call to the {@code next}
         * method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }

        /**
         * 检查迭代过程中，环有没有被修改（不能增加或减少节点）
         */
        final void checkForComodification() {
            if (modCount != expectedModCount) { throw new ConcurrentModificationException(); }
        }
    }
}

