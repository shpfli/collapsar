package pers.hubery.collapsar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pers.hubery.collapsar.entity.VirtualIdentity;
import pers.hubery.collapsar.service.VirtualIdentityManageService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollapsarApplicationTests {

	@Autowired
	private VirtualIdentityManageService virtualIdentityManageService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testAddVid() {

		VirtualIdentity virtualIdentity = new VirtualIdentity();
		virtualIdentity.setComment("Github");
		virtualIdentity.setWorld("GitHub");
		virtualIdentity.setIdentity("Hubery.Lee@outlook.com");
		virtualIdentity.setPassword("xbk11@github");

		virtualIdentityManageService.saveVirtualIdentity(virtualIdentity);
	}

	@Test
	public void testFindAll() {

		List<VirtualIdentity> virtualIdentityList = virtualIdentityManageService.findAll();
		Assert.assertNotNull("查询结果为null！", virtualIdentityList);
		Assert.assertNotEquals("查询结果列表为空！", virtualIdentityList.size(), 0);
		System.out.println(virtualIdentityList);
	}
}
