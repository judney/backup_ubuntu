package Controler;

public class IrradiacaoNormalDireta {
	
	
	String ID	   ; 
	String COUNTRY ; 
	String LON	   ; 
	String LAT	   ; 
	String ANNUAL  ; 
	String JAN	   ; 
	String FEB	   ; 
	String MAR	   ; 
	String APR	   ; 
	String MAY	   ; 
	String JUN	   ; 
	String JUL	   ; 
	String AUG	   ; 
	String SEP	   ; 
	String OCT	   ; 
	String NOV	   ; 
	String DEC     ;
	String[] vetPar = new String[16] ; 
	
	
	// arquivo directNormalMeans do atlas solar 2017 
	
	
	//public IrradiacaoNormalDireta() {} ; 

	public IrradiacaoNormalDireta(String[] vetPar ) {
      
	ID      = vetPar[0];
	COUNTRY = vetPar[1];
	LON 	= vetPar[2];
	LAT 	= vetPar[3];
	ANNUAL 	= vetPar[4];
	JAN 	= vetPar[5];
	FEB 	= vetPar[6];
	MAR 	= vetPar[7];
	APR 	= vetPar[8];
	MAY 	= vetPar[9];
	JUN 	= vetPar[10];
	JUL 	= vetPar[11];
	AUG 	= vetPar[12];
	SEP 	= vetPar[13];
	OCT 	= vetPar[14];
	NOV 	= vetPar[15];
	DEC 	= vetPar[16];

	} ; 
	
	
	/*
	public IrradiacaoNormalDireta(String iD, String cOUNTRY, String lON, String lAT, String aNNUAL, String jAN, String fEB,
			String mAR, String aPR, String mAY, String jUN, String jUL, String aUG, String sEP, String oCT, String nOV,
			String dEC) {
		
		ID = iD;
		COUNTRY = cOUNTRY;
		LON = lON;
		LAT = lAT;
		ANNUAL = aNNUAL;
		JAN = jAN;
		FEB = fEB;
		MAR = mAR;
		APR = aPR;
		MAY = mAY;
		JUN = jUN;
		JUL = jUL;
		AUG = aUG;
		SEP = sEP;
		OCT = oCT;
		NOV = nOV;
		DEC = dEC;
	}
    */ 
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCOUNTRY() {
		return COUNTRY;
	}

	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}

	public String getLON() {
		return LON;
	}

	public void setLON(String lON) {
		LON = lON;
	}

	public String getLAT() {
		return LAT;
	}

	public void setLAT(String lAT) {
		LAT = lAT;
	}

	public String getANNUAL() {
		return ANNUAL;
	}

	public void setANNUAL(String aNNUAL) {
		ANNUAL = aNNUAL;
	}

	public String getJAN() {
		return JAN;
	}

	public void setJAN(String jAN) {
		JAN = jAN;
	}

	public String getFEB() {
		return FEB;
	}

	public void setFEB(String fEB) {
		FEB = fEB;
	}

	public String getMAR() {
		return MAR;
	}

	public void setMAR(String mAR) {
		MAR = mAR;
	}

	public String getAPR() {
		return APR;
	}

	public void setAPR(String aPR) {
		APR = aPR;
	}

	public String getMAY() {
		return MAY;
	}

	public void setMAY(String mAY) {
		MAY = mAY;
	}

	public String getJUN() {
		return JUN;
	}

	public void setJUN(String jUN) {
		JUN = jUN;
	}

	public String getJUL() {
		return JUL;
	}

	public void setJUL(String jUL) {
		JUL = jUL;
	}

	public String getAUG() {
		return AUG;
	}

	public void setAUG(String aUG) {
		AUG = aUG;
	}

	public String getSEP() {
		return SEP;
	}

	public void setSEP(String sEP) {
		SEP = sEP;
	}

	public String getOCT() {
		return OCT;
	}

	public void setOCT(String oCT) {
		OCT = oCT;
	}

	public String getNOV() {
		return NOV;
	}

	public void setNOV(String nOV) {
		NOV = nOV;
	}

	public String getDEC() {
		return DEC;
	}

	public void setDEC(String dEC) {
		DEC = dEC;
	}

	@Override
	public String toString() {
		return "IrradiacaoNormalDireta [ID=" + ID + ", COUNTRY=" + COUNTRY + ", LON=" + LON + ", LAT=" + LAT
				+ ", ANNUAL=" + ANNUAL + ", JAN=" + JAN + ", FEB=" + FEB + ", MAR=" + MAR + ", APR=" + APR + ", MAY="
				+ MAY + ", JUN=" + JUN + ", JUL=" + JUL + ", AUG=" + AUG + ", SEP=" + SEP + ", OCT=" + OCT + ", NOV="
				+ NOV + ", DEC=" + DEC + "]";
	} 


	
	
	
}

