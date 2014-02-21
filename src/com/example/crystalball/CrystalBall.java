package com.example.crystalball;

import java.util.Random;


public class CrystalBall {
	String[] answers={
    		"It is Certain",
    		"It is Decidedly so",
    		"All Signs say Yes",
    		"The Stars are not Aligned",
    		"My Reply is No",
    		"It is Doubtfull",
    		"Better Not tell you Now",
    		"Concentrate and ask again"
    		
    };
	
	public String getAnswer(){
		
	Random generateRandom = new Random();
	int randomNo = generateRandom.nextInt(answers.length);
	
	String ans= "";
	ans = answers[randomNo];
    return ans;

}
}