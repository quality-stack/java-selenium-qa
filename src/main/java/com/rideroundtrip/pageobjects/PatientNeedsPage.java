package com.rideroundtrip.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import lombok.Getter;

public class PatientNeedsPage
{
	
//Selecting vehicle types: ambulatory,wheel chair or stretcher
	
	@FindBy(xpath="//*[@id=\"trip_vehicle_need_type_ambulatory\"]")
	private @Getter  WebElement ambulatory_vehicle;
	@FindBy(xpath="//*[@id=\"trip_vehicle_need_type_wheelchair\"]")
	private @Getter  WebElement wheelchair_vehicle;
	@FindBy(xpath="//*[@id=\"trip_vehicle_need_type_stretcher\"]")
	private @Getter  WebElement stretcher_vehicle;
	
//Elements when Vehicle type is  Ambulatory-Lyft:
	
	@FindBy(xpath="//*[@id=\"trip_transportation_type_ride_share\"]")
	private @Getter  WebElement lyft_ride;
	
//Kind of lyft patient needs
	@FindBy(xpath= "//input[@value='lyft']") 
	private @Getter  WebElement ridetype_lyft;
	@FindBy(xpath="//input[@value='lyft_plus']")
	private @Getter  WebElement ridetype_lyftXL;
	
//Elements when Vehicle type is  Ambulatory-medical sedan:
	
	@FindBy(id=("trip_transportation_type_medical_sedan"))
	private @Getter  WebElement medical_sedan;
	
	
//Elements when Vehicle type is wheelchair:
	
	  //who will bring wheelchair
		 @FindBy(xpath="//input[@value='The patient will bring his/her own wheelchair.']")
	     private @Getter  WebElement ownwheelchair;
	     @FindBy(xpath="//input[@value='The driver will need to bring a wheelchair.']")
	     private @Getter  WebElement driverwheelchair;
	
	  //on oxygen?
	    @FindBy(xpath="//input[@value='The patient is not on oxygen.']")
	    private @Getter  WebElement no_oxygen;
	    @FindBy(xpath="//input[@value='The patient will bring their own oxygen.']")
	    private @Getter  WebElement own_oxygen;
	

//transportation company need to provide a car seat?
	    	
	@FindBy(xpath="//input[@value='No car seat is needed or the patient caregiver will provide the car seat.']")
	private @Getter  WebElement Nocarseatneeded;
	@FindBy(xpath="//input[@value='The driver will need to provide a car seat.']")
	private @Getter  WebElement carseatneeded;
	
	  //Will there be stairs
		@FindBy(xpath="//input[@value='no_stairs']")
		private @Getter  WebElement no_stairs;
		@FindBy(xpath="//input[@value='stairs']")
		private @Getter  WebElement has_stairs;
		@FindBy(xpath="//div[@class='field -hidden -no-bottom-spacing is-active -error']//input[@placeholder='3']")
		private @Getter  WebElement numberofstairs;
		@FindBy(name=("	trip[room_number]"))
		private @Getter  WebElement roomnumber;
		
//Elements when Vehicle type is Stretcher:	
		
	  //level of transport does the patient require?
		@FindBy(xpath="//h3[contains(text(),'Stretcher Van')]")
		private @Getter  WebElement stretcher_van_transport;

		// Selecting CheckBox	
		@FindBy(xpath="//input[@value='Patient does not require an ambulance, but needs a Stretcher Van']")
		private @Getter  WebElement no_ambulance_checkbox;
		@FindBy(xpath="//input[@value='Patient requires oxygen']")
		private @Getter  WebElement patientrequiresoxygen_checkbox;
		
		//Transport types: Bls,als,sct/cct
		
		@FindBy(xpath="//h3[contains(text(),'Basic Life Support (BLS)')]")
		private @Getter  WebElement bls_transport;	
		
		// Selecting CheckBox
		   @FindBy(xpath="//input[@value='Requires assistance to administer oxygen en route']")
		    private @Getter  WebElement bls_Requiresassistance_checkbox;
		   @FindBy(xpath="//input[@value='Patient is unable to sit in a wheelchair']")
		    private @Getter  WebElement bls_unabletosit_checkbox;
		   @FindBy(xpath="//input[@value='Orthopedic device that prevents transport by wheelchair or other means']")
		    private @Getter  WebElement bls_Orthopedicdevice_checkbox;
		   @FindBy(xpath="//input[@value='Disoriented, requires assistance/supervision and does not have own attendant']")
		    private @Getter  WebElement bls_Disoriented_checkbox;
		   @FindBy(xpath="//input[@value='Psychiatric patient, may require restraints, isolation precautions, and/or flight risk']")
		    private @Getter  WebElement bls_Psychiatricpatient_checkbox;
		   @FindBy(xpath="//input[@value='Aspiration precautions, requires suctioning or airway control en route']")
		    private @Getter  WebElement bls_aspirationprecautions_checkbox;
		   @FindBy(xpath="//input[@value='Sedated with IV or IM (including narcotics within last 30 minutes)']")
		    private @Getter  WebElement bls_sedatedwith_checkbox;
		   
		@FindBy(xpath="//h3[contains(text(),'Advanced Life Support (ALS)')]")
		private @Getter  WebElement als_transport;	   
		
		// Selecting CheckBox
		    @FindBy(xpath="//input[@value='ECG monitoring required en route']")
		    private @Getter  WebElement als_ecgmonitoring_checkbox;
		    @FindBy(xpath="//input[@value='IV requiring titration and continuous monitoring during transport (medications per local protocols)']")
		    private @Getter  WebElement als_titration_checkbox;
		    @FindBy(xpath="//input[@value='Tracheotomy care with deep suctioning']")
		    private @Getter  WebElement als_Tracheotomy_checkbox;
		    @FindBy(xpath="//input[@value='Chest tube to gravity']")
		    private @Getter  WebElement als_Chesttubegravity_checkbox;		    
		    @FindBy(xpath="//input[@value='Advanced airway management (including ventilating)']")
		    private @Getter  WebElement als_advancedairway_checkbox;
		    @FindBy(xpath="//input[@value='Chest tube requires suctioning']")
		    private @Getter  WebElement als_Chesttubesuctioning_checkbox;
		    
		@FindBy(xpath="//h3[contains(text(),'Specialty / Critical Care Transport (SCT/CCT)')]")
		private @Getter  WebElement sct_cct_transport;
		
		// Selecting CheckBox
		    @FindBy(xpath="//input[@value='Critical care nursing skills needed during transport']")
		    private @Getter  WebElement sct_critical_checkbox;
			@FindBy(xpath="//input[@value='Open central line or needs monitoring']")
		    private @Getter  WebElement sct_opencentral_checkbox;
		    @FindBy(xpath="//input[@value='Pulmonary artery line in place']")
		    private @Getter  WebElement sct_Pulmonary_checkbox;
		    @FindBy(xpath="//input[@value='Temporary pacemaker in place']")
		    private @Getter  WebElement sct_pacemaker_checkbox;		    
		    @FindBy(xpath="//input[@value='Transcutaneous or transvenous pacing']")
		    private @Getter  WebElement sct_Transcutaneous_checkbox;
		    @FindBy(xpath="//input[@value='Ventilator dependent during transport']")
		    private @Getter  WebElement sct_ventilator_checkbox;
		    
 //Patient details
		
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[3]/form[1]/div[1]/div[3]/div[1]/div[3]/div[3]/div[1]/input[1]")
	private @Getter  WebElement patient_dob;	
	@FindBy(name=("patient[weight]"))
	private @Getter  WebElement patient_weight;
	
	@FindBy(id=("height_feet"))
	private @Getter  WebElement height_feet;
	 //dropdown options
         @FindBy(xpath="//input[@value='1']")
          private @Getter  WebElement height_1feet;
         @FindBy(xpath="//input[@value='2']")
         private @Getter  WebElement height_2feet;
         @FindBy(xpath="//input[@value='3']")
         private @Getter  WebElement height_3feet;
         @FindBy(xpath="//input[@value='4']")
         private @Getter  WebElement height_4feet;
         @FindBy(xpath="//input[@value='5']")
         private @Getter  WebElement height_5feet;
         @FindBy(xpath="//input[@value='6']")
         private @Getter  WebElement height_6feet;
         @FindBy(xpath="//input[@value='7']")
         private @Getter  WebElement height_7feet;
         @FindBy(xpath="//input[@value='8']")
         private @Getter  WebElement height_8feet;
	
	@FindBy(id=("height_inches"))
	private @Getter  WebElement height_inches;
	
	
	@FindBy(id=("patient_gender"))
	private @Getter  WebElement drpgender;
	 //dropdown options
        @FindBy(xpath="//input[@value='male']")
        private @Getter  WebElement drpgender_male;
        @FindBy(xpath="//input[@value='female']")
        private @Getter  WebElement drpgender_female;
        @FindBy(xpath="//input[@value='other']")
        private @Getter  WebElement drpgender_other;
        
        
//Reasons and instructions
	
	@FindBy(name=("trip[reason_id]"))
	private @Getter  WebElement drptripreason;
	      
	@FindBy(name=("trip[additional_info]"))
	private @Getter  WebElement additional_information;	
	@FindBy(name=("trip[special_instructions]"))
	private @Getter  WebElement driver_instructions;

//Modal elements
    // driver.switchTo().frame("ModelFrameTitle");
	//div[@id='vehicle-type-confirmation']//i[@class='modal-close']

//Book a BLS vehicle button
	@FindBy(xpath="//button[contains(text(),'Book a BLS Vehicle')]")
	private @Getter  WebElement book_bls_btn;
//Continue with selected vehicle button
	@FindBy(xpath="//button[contains(text(),'Continue With Selected Vehicle')]")
	private @Getter  WebElement continue_selected_btn;	
	
//PCS form
	@FindBy(id=("upload-pcs-button"))
	private @Getter  WebElement uploadpcs;
	@FindBy(id=("create-pcs-button"))
	private @Getter  WebElement createpcs;
	
	//create pcs modal
	@FindBy(id=("pcs-information"))
	private @Getter  WebElement createpcsmodal;
	@FindBy(id=("physician_name"))
	private @Getter  WebElement physician_name;
	@FindBy(id=("physician_phone"))
	private @Getter  WebElement physician_phone;
	@FindBy(id=("physician_fax"))
	private @Getter  WebElement physician_fax;	
	@FindBy(id=("nursing_unit_phone"))
	private @Getter  WebElement nursing_unit_phone;	
	@FindBy(id=("closest_facility_true"))
	private @Getter  WebElement closest_facility_true;	
	@FindBy(id=("closest_facility_false"))
	private @Getter  WebElement closest_facility_false;	
	@FindBy(id=("no_wheelchair_reason"))
	private @Getter  WebElement unabletosit;	
	
	@FindBy(id=("pcs_signer_title"))
	private @Getter  WebElement drp_pcs_signer_title;
	         
	@FindBy(id=("pcs_signer_signature"))
	private @Getter  WebElement pcs_signer_signature; 
	//nevermind link
	@FindBy(linkText="Nevermind")
	private @Getter WebElement Nevermind_link;
	
	//Back link
	@FindBy(linkText="Back")
	private @Getter WebElement Back_link;	
	
	//Continue button
	 @FindBy(xpath="//button[@class='button -inline']")
	  private @Getter  WebElement Continue_btn;
	
	
	public PatientNeedsPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

//Selecting vehicle types: ambulatory,wheel chair or stretcher
	public WebElement getambulatory_vehicle() {
		return getAmbulatory_vehicle();
	}
	
	public WebElement getwheelchair_vehicle() {
		return wheelchair_vehicle;
	}
	
	public WebElement getstretcher_vehicle() {
		return stretcher_vehicle;
	}

//when Vehicle type is  Ambulatory-lyft:
	
	public WebElement getlyft_ride() {
		return lyft_ride;
	}
	
	public WebElement getridetype_lyft() {
		return ridetype_lyft;
	}
	
	public WebElement getridetype_lyftXL() {
		return ridetype_lyftXL;
	}
	
//when Vehicle type is  Ambulatory-medical sedan:
	
	public WebElement getmedical_sedan() {
		return medical_sedan;
	}
	
	public WebElement getNocarseatneeded() {
		return Nocarseatneeded;
	}
	
	public WebElement getcarseatneeded() {
		return carseatneeded;
	}
	
//Stairs
	public WebElement getno_stairs() {
		return no_stairs;
	}
	
	public WebElement gethas_stairs() {
		return has_stairs;
	}
	
	public WebElement getnumberofstairs() {
		return numberofstairs;
	}
	
//patient details
	public WebElement getpatient_dob() {
		return patient_dob;
	}
	
	public WebElement getpatient_weight() {
		return patient_weight;
	}
	
	public WebElement getdrpgender() {
		return drpgender;
	}
	  //drop down options
	     public WebElement getdrpgender_male() {
		 return drpgender_male;
	     }
	     public WebElement getdrpgender_female() {
		 return drpgender_female;
		     }
	     public WebElement getdrpgender_other() {
		 return drpgender_other;
		     }
//room number	
	public WebElement getroomnumber() {
		return roomnumber;
	}
	
//when Vehicle type is wheel chair:
	//who will bring wheel chair
	
	public WebElement getownwheelchair() {
		return ownwheelchair;
	}
	public WebElement getdriverwheelchair() {
		return driverwheelchair;
	}
	
	
	public WebElement getno_oxygen() {
		return no_oxygen;
	}
	public WebElement getown_oxygen() {
		return own_oxygen;
	}
	
//when Vehicle type is stretcher:
	//level of transport does the patient require?
	
	public WebElement getstretcher_van_transport() {
		return stretcher_van_transport;
	}
	public WebElement getno_ambulance_checkbox() {
		return no_ambulance_checkbox;
	}
	public WebElement getpatientrequiresoxygen_checkbox() {
		return patientrequiresoxygen_checkbox;
	}
	
	//bls transport
	public WebElement getbls_transport() {
		return bls_transport;
	}
	public WebElement getbls_Requiresassistance_checkbox() {
		return bls_Requiresassistance_checkbox;
	}
	public WebElement getbls_unabletosit_checkbox() {
		return bls_unabletosit_checkbox;
	}
	public WebElement getbls_Orthopedicdevice_checkbox() {
		return bls_Orthopedicdevice_checkbox;
	}
	public WebElement getbls_Disoriented_checkbox() {
		return bls_Disoriented_checkbox;
	}
	public WebElement getbls_Psychiatricpatient_checkbox() {
		return bls_Psychiatricpatient_checkbox;
	}
	public WebElement getbls_aspirationprecautions_checkbox() {
		return bls_aspirationprecautions_checkbox;
	}
	public WebElement getbls_sedatedwith_checkbox() {
		return bls_sedatedwith_checkbox;
	}
	
	//als transport
	
	public WebElement getals_transport() {
		return als_transport;
	}
	public WebElement getals_ecgmonitoring_checkbox() {
		return als_ecgmonitoring_checkbox;
	}
	public WebElement getals_titration_checkbox() {
		return als_titration_checkbox;
	}
	public WebElement getals_Tracheotomy_checkbox() {
		return als_Tracheotomy_checkbox;
	}
	public WebElement getals_Chesttubegravity_checkbox() {
		return als_Chesttubegravity_checkbox;
	}
	public WebElement getals_advancedairway_checkbox() {
		return als_advancedairway_checkbox;
	}
	public WebElement getals_Chesttubesuctioning_checkbox() {
		return als_Chesttubesuctioning_checkbox;
	}
		
	//cct/sct transport
	
	public WebElement getsct_cct_transport() {
		return sct_cct_transport;
	}
	public WebElement getsct_critical_checkbox() {
		return sct_critical_checkbox;
	}
	public WebElement getsct_opencentral_checkbox() {
		return sct_opencentral_checkbox;
	}
	public WebElement getsct_Pulmonary_checkbox() {
		return sct_Pulmonary_checkbox;
	}
	public WebElement getsct_pacemaker_checkbox() {
		return sct_pacemaker_checkbox;
	}
	public WebElement getsct_Transcutaneous_checkbox() {
		return sct_Transcutaneous_checkbox;
	}
	public WebElement getsct_ventilator_checkbox() {
		return sct_ventilator_checkbox;
	}
	
	
//Reasons and instructions
	public WebElement getdrptripreason() {
		return drptripreason;
	}	
	
	public WebElement getadditional_information() {
		return additional_information;
	}
	
	public WebElement getdriver_instructions() {
		return driver_instructions;
	}
	
//Modal elements	
	public WebElement getbook_bls_btn() {
		return book_bls_btn;
	}
	
	public WebElement getcontinue_selected_btn() {
		return continue_selected_btn;
	}
	
//PCS form
	
	public WebElement getuploadpcs() {
		return uploadpcs;
	}
	
	public WebElement getcreatepcs() {
		return createpcs;
	}
	 
	//create PCS form
	public WebElement getcreatepcsmodal() {
		return createpcsmodal;
	}
	 
	public WebElement getphysician_name() {
		return physician_name;
	}
	public WebElement getphysician_phone() {
		return physician_phone;
	}
	public WebElement getphysician_fax() {
		return physician_fax;
	}
	public WebElement getnursing_unit_phone() {
		return nursing_unit_phone;
	}
	public WebElement getclosest_facility_true() {
		return closest_facility_true;
	}
	public WebElement getclosest_facility_false() {
		return closest_facility_false;
	}
	public WebElement getunabletosit() {
		return unabletosit;
	}
	
	//dropdown options
	
	public WebElement getpcs_signer_signature() {
		return pcs_signer_signature;
	}
	public WebElement getNevermind_link() {
		return Nevermind_link;
	}
	
	
	public WebElement getBack_link() {
		return Back_link;
	}
	
	public WebElement getContinue_btn() {
		return 	Continue_btn;
	}

	public WebElement getAmbulatory_vehicle() {
		return ambulatory_vehicle;
	}

	public void setAmbulatory_vehicle(WebElement ambulatory_vehicle) {
		this.ambulatory_vehicle = ambulatory_vehicle;
	}
		
}
