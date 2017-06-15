package com.tronker.constants;

public class WheelConstant {
	//状态
	public static final int NORMAL_STATUS = 1;
	public static final int PREVIOUS_STATUS = 0;
	public static final int FORBIDEN_STATUS = 2;
	//1级到6级
	public static final int FIRST = 1;
	public static final int SECOND = 2;
	public static final int THIRD = 3;
	public static final int FOURTH = 4;
	public static final int FIFTH = 5;
	public static final int SIXTH = 6;
	public static final int SEVENTH = 7;
	//初始经验值
	public static final int INIT_EXPERIENCE = 0;
	
	//等级的
	public static final String GAME_LEVEL_EXPERIENCE = "GAME_LEVEL_EXPERIENCE";
	public static final String LEVEL_FIRST = "LEVEL_FIRST";
	public static final String LEVEL_SECOND = "LEVEL_SECOND";
	public static final String LEVEL_THIRD = "LEVEL_THIRD";
	public static final String LEVEL_FOURTH = "LEVEL_FOURTH";
	public static final String LEVEL_FIFTH = "LEVEL_FIFTH";
	public static final String LEVEL_SIXTH = "LEVEL_SIXTH";
	public static final String LEVEL_SEVENTH = "LEVEL_SEVENTH";
	
	//运营时间段
	public static final String STAGE_TIME = "STAGE_TIME";
	public static final String FIRST_STAGE_START = "FIRST_STAGE_START";
	public static final String FIRST_STAGE_END = "FIRST_STAGE_END";
	public static final String SECOND_STAGE_START = "SECOND_STAGE_START";
	public static final String SECOND_STAGE_END = "SECOND_STAGE_END";
	public static final String THIRD_STAGE_START = "THIRD_STAGE_START";
	public static final String THIRD_STAGE_END = "THIRD_STAGE_END";
	
	public static final int CHANNEL_APP_ID=1;
	public static final int CHANNEL_ID=1;
	public static final int APP_ID=1;
	
	public static final String doubleHit = "game:doubleHit:";
	public static final String preCombo = "game:preCombo:";
	
	public static final String randomValue = "game:randomValue:";
	public static final String combo = "game:combo:";
	public static final String nextOperate = "nextOperate";
	public static final String needBreak = "needBreak";
	
	public static final String  currentExperience= "currentExperience";
	public static final String  totalExperienceGoal= "totalExperienceGoal";
	
	public static final String  previousExperience= "previousExperience";
	public static final String  previousGrade= "previousGrade";
	public static final String  previousExperienceGoal = "previousExperienceGoal";
	
	public static final String  opratedExperience= "opratedExperience";
	public static final String  operatedGrade= "operatedGrade";
	public static final String  operatedExperienceGoal = "operatedExperienceGoal";
	
	public static final String nextExperieceGoal = "nextExperieceGoal";
	
	public static final String userTokenInvalid = "user token(sign) is invalid";
	
	public static final String combo2 = "combo";
	
	public static final String REDIS = "REDIS";
	public static final String REDIS_HOST = "REDIS_HOST";
	public static final String REDIS_PORT = "REDIS_PORT";
	
	public static final String validOperateCount = "game:validOperateCount:";
	
	public static final String unionOperateCount = "game:unionOperateCount:";
}
