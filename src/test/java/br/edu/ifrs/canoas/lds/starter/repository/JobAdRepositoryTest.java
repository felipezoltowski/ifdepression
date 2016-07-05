package br.edu.ifrs.canoas.lds.starter.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.edu.ifrs.canoas.lds.starter.IFSkillsApplication;
import br.edu.ifrs.canoas.lds.starter.domain.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(IFSkillsApplication.class)
public class JobAdRepositoryTest {
	
	@Autowired
	JobAdRepository jobAdRepository;
	
	/**
	 *  @author Luciane
	 * Date: 23/04/2016
	 * Description: Test method find by all property of Job Ad
	 */
	@Test
	public void testFindAllByPropertiesOfJobAd() {
		String str = "Nível hierárquico: Operacional, Regime de contratação de tipo Efetivo – CLT Jornada Período Integral";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(1));
		str="SCRUM";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(2));
		str="IT";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(7));
		str="Graduation in IT area";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(3));
		str="Meal ticket";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(5));
		str="rh.it@gmail.com";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(4));
		str="Luciane";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(1));
		str="DBA Architect";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(1));
		str="waiting";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(4));
		str="Canoas";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(4));
		str="java";
		assertThat(jobAdRepository.findAllByDescriptionContainingOrRequirementsContainingOrBusinessAreaContainingOrEducationLevelRequiredContainingOrBenefitsContainingOrContactInfoContainingOrEmployer_FullNameContainingOrTitleContainingOrStatusOrLocationPlaceDescriptionContainingOrTagsContainingAllIgnoreCase(
				str, str,str, str, str, str, str, str, Status.get(str), str, str).size(), is(3));		
	}
	
	/**
	*  @author Luciane
	* Date: 25/04/2016
	* Description: Method of test search for status
	*
	**/
	@Test
	public void testFindByStatus() {
		assertThat(jobAdRepository.findByStatus(Status.get("Approved")).size(), is(4));
		assertThat(jobAdRepository.findByStatus(Status.get("Waiting")).size(), is(4));
		assertThat(jobAdRepository.findByStatus(Status.get("Rejected")).size(), is(0));
	}

}
