package com.test.jni;

public class ConchServiceInterface {
	public native String IndicatorCompute(String strJson);  

	public static void main(String args[]) {
		System.loadLibrary("ConchServiceAlgo");
		
		ConchServiceInterface test=new ConchServiceInterface();

		String strResult=test.IndicatorCompute("{\"slzb\":{\"KH\":\"0.95\",\"SM\":\"2.6\",\"IM\":\"2.1\"},"+
				"\"ylpb\":{\"shs\":\"12\",\"fmh\":\"23\",\"sy\":\"42\",\"tf\":\"54\"},"+
				"\"slzhxcf\":\"32\","+
				"\"mdgyfx\":{\"aad\":\"76\",\"qnetad\":\"64\",\"slrh\":\"43\"},"+
				"\"shscf\":{"+
				"\"Loss\":{\"max\":\"54\",\"min\":\"50\"},"+
				"\"SiO2\":{\"max\":\"89\",\"min\":\"70\"},"+
				"\"Al2O3\":{\"max\":\"78\",\"min\":\"60\"},"+
				"\"Fe2O3\":{\"max\":\"67\",\"min\":\"60\"},"+
				"\"CaO\":{\"max\":\"90\",\"min\":\"70\"},"+
				"\"MgO\":{\"max\":\"67\",\"min\":\"60\"}"+
				"},"+
				"\"ylcf\":{"+
				"\"mh\":{"+
				"\"Loss\":\"51\","+
				"\"SiO2\":\"72\","+
				"\"Al2O3\":\"63\","+
				"\"Fe2O3\":\"64\","+
				"\"CaO\":\"75\","+
				"\"MgO\":\"66\","+
				"\"SO3\":\"67\","+
				"\"K2O\":\"68\","+
				"\"Na2O\":\"69\","+
				"\"R2O\":\"60\","+
				"\"水分\":\"61\""+
				"},"+
				"\"fmh\":{"+
				"\"Loss\":\"61\","+
				"\"SiO2\":\"82\","+
				"\"Al2O3\":\"73\","+
				"\"Fe2O3\":\"74\","+
				"\"CaO\":\"85\","+
				"\"MgO\":\"76\","+
				"\"SO3\":\"77\","+
				"\"K2O\":\"78\","+
				"\"Na2O\":\"79\","+
				"\"R2O\":\"70\","+
				"\"水分\":\"71\""+
				"},"+
				"\"sy\":{"+
				"\"Loss\":\"41\","+
				"\"SiO2\":\"62\","+
				"\"Al2O3\":\"53\","+
				"\"Fe2O3\":\"54\","+
				"\"CaO\":\"65\","+
				"\"MgO\":\"56\","+
				"\"SO3\":\"57\","+
				"\"K2O\":\"58\","+
				"\"Na2O\":\"59\","+
				"\"R2O\":\"50\","+
				"\"水分\":\"51\""+
				"},"+
				"\"tf\":{"+
				"\"Loss\":\"31\","+
				"\"SiO2\":\"52\","+
				"\"Al2O3\":\"43\","+
				"\"Fe2O3\":\"44\","+
				"\"CaO\":\"55\","+
				"\"MgO\":\"46\","+
				"\"SO3\":\"47\","+
				"\"K2O\":\"48\","+
				"\"Na2O\":\"49\","+
				"\"R2O\":\"40\","+
				"\"水分\":\"41\""+
				"}}}");
		System.out.println("Result="+strResult);
		
		System.out.println("Test");
	}
}
