package com.yslm.util.generate;

import java.util.Random;

/**
 * 随机类
 * 生成方式参考mian()
 *
 */
public class RandomUitls {
	 //生成类型
    RandowType randowType= RandowType.NUMBER;
    //生成长度
    private int strLength=0;
	
    /**
     * 左补充0
     * @param num
     * @param size
     * @return
     */
	public static String padLeft(int num,int size){
		String numStr=num+"";
		if(numStr.length() >size){
			System.err.println("数字过大:"+num+" Size:"+size);
			System.exit(0);
		}
		StringBuffer sb =new StringBuffer();
		for(int i=numStr.length();i<size;i++)
			sb.append("0");
		
		sb.append(numStr);
		return sb.toString();
	}
	/**
	 * 根据模板生成随机数
	 * 1111 aaa BBB
	 * @param formation
	 * @return  1111aaaBBB
	 */
	public static String createRandom(String formation){
		StringBuffer sb =new StringBuffer();
		for(String str : formation.split(" ")){
			RandomUitls random =new RandomUitls();
			random.transformation(str);
			sb.append(random._createRandom(random.getStrLength()));
		}
		return sb.toString();
	}
	
	/**
	 * 转化
	 * @param str
	 */
	private void transformation(String str){
		setStrLength(str.length());
		setRandowType(RandowType.vf(str));
	}
	
	private String _createRandom(int numSize){
		StringBuffer sb =new StringBuffer();
		if(RandowType.NUMBER.equals(getRandowType())){//数字
			Random numRandom=new Random();
			return padLeft(numRandom.nextInt(Double.valueOf(Math.pow(10, numSize)).intValue()),numSize);
		}
		for(int i=0;i<numSize;i++){
			String range=getRandowType().getRange();
			char randomStr=range.charAt((int)(Math.random() * range.length()));
			sb.append(randomStr);
		}
		
		return sb.toString();
	}
	
	
	
	
	private RandowType getRandowType() {
		return randowType;
	}

	private void setRandowType(RandowType randowType) {
		this.randowType = randowType;
	}

	private int getStrLength() {
		return strLength;
	}

	private void setStrLength(int strLength) {
		this.strLength = strLength;
	}


	enum RandowType{
		/**数字**/
		NUMBER("0123456789"),
		/**数字加小写字母**/
		NUMBER_Lowercase("0123456789abcdefghijklmnopqrstuvwxyz"),
		/**数字加大写字母**/
		NUMBER_Capital("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
		/**
		 * 如果增加随机类型需要补充该方法
		 * @param str
		 * @return
		 */
		public static RandowType vf(String str){
			char c=str.charAt(0);
			if(Character.isDigit(c)){
				return RandowType.NUMBER;
			}else if(Character.isLowerCase(c)){
				return RandowType.NUMBER_Lowercase;
			}else if(Character.isUpperCase(c)){
				return RandowType.NUMBER_Capital;
			}
			return null;
		}
		
		
		private String range;
		
		private RandowType(String range) {
			this.range = range;
		}

		public void setRange(String range) {
			this.range = range;
		}

		public String getRange() {
			return range;
		}

	}
	
	public static void main(String[] args) {
		System.out.println(RandomUitls.createRandom("1111"));
		System.out.println(RandomUitls.createRandom("1111 AAA bbb"));
	}
}
