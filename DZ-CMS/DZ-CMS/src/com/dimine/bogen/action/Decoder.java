package com.dimine.bogen.action;

/**
 * 
 * @author Aaron
 * CreatTime 2014-12-12
 * Grid的解码类
 */
public class Decoder {
	public static final String CELDIV="##,##";
	public static final String ROWDIV=",,#,,";
	public static final String NULLDATA = "NULLDATA";
	public static String[][] decode(String input)
	{
		System.out.println("commit value="+input);
		if(input.trim().length()==0)
		{
			return null;
		}	
		String[] rowDatas =input.split(ROWDIV);
		String[] celDatas = rowDatas[0].split(CELDIV);
		String[][] a = new String[rowDatas.length][celDatas.length];
		
		for(int i = 0 ; i <rowDatas.length;i++)
		{
			String[] temp = rowDatas[i].split(CELDIV);
			a[i]=rowDatas[i].split(CELDIV);
		}
		
		for(int i = 0 ; i <a.length;i++)
		{			
			String[] temp= a[i];
			for(int j=0;j<temp.length;j++)
			{
				if(a[i][j].equals(NULLDATA))
				{
					a[i][j]="";
				}
			}
		}
		return a;
		
	}
	public static void main(String[] args) {
		System.out.println("Grid的解码处理");
	}
}
