/**
 * @author:
 * @date: 
 * @description: 
 */
package br.edu.ifrs.canoas.lds.starter.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ItemServiceTest.class
	, ArticleServiceTest.class
	, CommentServiceTest.class
	, NotificationServiceTest.class
	, JobAdServiceTest.class
	, ArticleRankServiceTest.class
	, PostServiceTest.class})
public class AllTests {

}
