package br.edu.ifrs.canoas.lds.starter.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;
import br.edu.ifrs.canoas.lds.starter.controller.BaseControllerTest;
import br.edu.ifrs.canoas.lds.starter.domain.JobAd;
import br.edu.ifrs.canoas.lds.starter.domain.Status;
import br.edu.ifrs.canoas.lds.starter.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class JobAdServiceTest  extends BaseControllerTest{
	
	@Autowired
	JobAdService jobAdService;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * @author Luciane
	 * Date: 18/04/2016
	 * Description: Test to list all job's.
	 */
	@Test
	public void testToListAllJobAdsStatusApproved() {
		assertTrue(jobAdService.listStatusApproved().spliterator().estimateSize() > 0);
	}

	/**
	 *  @author Luciane
	 * Date: 18/04/2016
	 * Description: Test to get jobAd1 and check title.
	 */
	@Test
	public void testToGetJobAd1AndCheckTitle() {
		assertThat(jobAdService.get(1L), hasProperty("requirements", is("Escolaridade Mínima: Ensino Superior Inglês (Intermediário - Requerido) Programação: C#, JavaScript, jQuery, PHP")));
	}
	
	
	/**
	 *  @author Luciane
	 * Date: 18/04/2016
	 * Description: Test to find jobAd4 delete it and check again.
	 */
	@Test  
	public void testToFindJobAd4DeleteItAndCheckAgain() {
		assertThat(jobAdService.get(4L), hasProperty("businessArea", is("Marketing")));
		jobAdService.delete(4L);
		assertThat(jobAdService.get(4L), is(nullValue()));
	}
	

	/**
	 *  @author Luciane
	 * Date: 19/04/2016
	 * Description: Test the method list that
	 *  returns a list as a result of search
	 */
	@Test
	public void testListString() {
		assertThat(jobAdService.list("Informática"), hasSize(1));
	}
	
	
	/**
	 * @author Luciane
	 * Date: 19/04/2016
	 * Description: Test method SendMessage
	 */
	@Test
	public void testSendMessage() {
		JobAd jobAd = new JobAd();
		jobAd.setEmployer(userRepository.findOne(9L));
		jobAd.setDescription("My description job");
		jobAd.setBusinessArea("This is the job's business area.");
		jobAd.setRequirements("This is the job's requirements!!!");
			
		assertThat(jobAdService.sendMessage(jobAd), is(notNullValue()));
		assertThat(jobAdService.sendMessage(jobAd), hasProperty("to",is(not(empty()))));
		
	}
	
	/**
	 * @author Aline G.
	 * Date: 27/04/2016
	 * Description: Test method SendEvaluationMessage with status Approved
	 */
	@Test
	@Ignore
	public void testSendEvaluationMessageApproved() {
		JobAd jobAd = jobAdService.get(7L);			
		assertThat(jobAdService.sendEvaluationMessage(jobAd), is(notNullValue()));
		assertThat(jobAdService.sendEvaluationMessage(jobAd), hasProperty("to",is(not(empty()))));
	}
	
	/**
	 * @author Aline G.
	 * Date: 27/04/2016
	 * Description: Test method SendEvaluationMessage with status Rejected
	 */
	@Test
	public void testToSendEvaluationMessageRejected() {
		JobAd jobAd = new JobAd();
		jobAd.setEmployer(userRepository.findOne(8L));
		jobAd.setTitle("PHP developer");
		jobAd.setBenefits("Voucher");
		jobAd.setBusinessArea("IT");
		jobAd.setStatus(Enum.valueOf(Status.class, "Rejected"));
		jobAd.setJustification("This ad was rejected because there was a bad job ad");
		jobAd.setDescription("This is an ad of a PHP development job");
		
		assertThat(jobAdService.sendEvaluationMessage(jobAd), is(notNullValue()));
		assertThat(jobAdService.sendEvaluationMessage(jobAd), hasProperty("to",is(not(empty()))));	
		
	}
	
	

	/**
	 * @author Aline G
	 * Date: 27/04/2016
	 * Description: Test to list all jobAds with waiting status.
	 */
	@Test
	public void testToListAllWaitingJobAds() {
		assertTrue(jobAdService.listWaitingJobAds().spliterator().estimateSize() > 0);
	}
	
	
	

}
