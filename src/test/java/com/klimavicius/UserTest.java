package com.klimavicius;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class UserTest {

	public UserTest() {
		
	}

	@Test
	public void testAccount() {
		User testUser = new User("user@user.com", "user", "userpassword");
		boolean check = testUser == null ? true : false;
		assertFalse(check);
	}

}
