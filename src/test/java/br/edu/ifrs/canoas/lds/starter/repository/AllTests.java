/**
 * @author:
 * @date: 
 * @description: 
 */
package br.edu.ifrs.canoas.lds.starter.repository;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ItemRepositoryTest.class
	, ArticleRepositoryTest.class
	, CommentRepositoryTest.class
	, JobAdRepositoryTest.class})
public class AllTests {

}
