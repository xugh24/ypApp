/*
 * File Name: AnimUtils.java 
 * History:
 * Created by Siyang.Miao on 2012-3-22
 */
package com.yuepang.yuepang.Util;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LayoutAnimationController;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class AnimUtils {
	//==========================================================================
	// Constants
	//==========================================================================
    public static final long FLASH = 100;
    public static final long SHORT = 300;
    public static final long MEDIUM = 500;
    public static final long LONG = 1000;

	//==========================================================================
	// Fields
	//==========================================================================

	//==========================================================================
	// Constructors
	//==========================================================================

	//==========================================================================
	// Getters
	//==========================================================================

	//==========================================================================
	// Setters
	//==========================================================================

	//==========================================================================
	// Methods
	//==========================================================================
	public static Animation makeSlideInAnim (boolean fromLeft, boolean withAlpha, boolean overshoot) {
		boolean shareIntepolator = !(withAlpha || overshoot);
		AnimationSet ret = new AnimationSet(shareIntepolator);
		Animation anim;
		// Translate
		anim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, fromLeft ? -1f : 1f, Animation.ABSOLUTE, 0,
				Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
		if (!shareIntepolator) {
			if (overshoot) {
				anim.setInterpolator(new OvershootInterpolator(1f));
			} else {
				anim.setInterpolator(new DecelerateInterpolator());
			}
		}
		ret.addAnimation(anim);
		// Alpha
		if (withAlpha) {
			anim = new AlphaAnimation(0f, 1f);
			anim.setInterpolator(new LinearInterpolator());
			ret.addAnimation(anim);
		}

		ret.setDuration(SHORT);
		if (shareIntepolator) {
			ret.setInterpolator(new DecelerateInterpolator());
		}
		return ret;
	}
	
	public static Animation makeSlideOutAnim (boolean toLeft, boolean withAlpha) {
		boolean shareIntepolator = !withAlpha;
		AnimationSet ret = new AnimationSet(shareIntepolator);
		Animation anim;
		// Translate
		anim = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.RELATIVE_TO_PARENT, toLeft ? -1f : 1f,
		        Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
		if (!shareIntepolator) {
			anim.setInterpolator(new AccelerateInterpolator());
		}
		ret.addAnimation(anim);
		// Alpha
		if (withAlpha) {
			anim = new AlphaAnimation(1f, 0f);
			anim.setInterpolator(new LinearInterpolator());
			ret.addAnimation(anim);
		}

		ret.setDuration(SHORT);
		if (shareIntepolator) {
			ret.setInterpolator(new AccelerateInterpolator());
		}
		return ret;
	}
	
    public static Animation makePopupInAnim() {
    	return makePopupInAnim(true);
    }
    
    public static Animation makePopupInAnim(boolean withAlpha) {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        if (withAlpha) {
        	// Create a fade in alpha animation
	        anim = new AlphaAnimation(0f, 1f);
	        anim.setDuration(SHORT);
	        anim.setInterpolator(new LinearInterpolator());
	        ret.addAnimation(anim);
        }
        // Create a bottom pop up animation
        anim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0f, 
                Animation.RELATIVE_TO_SELF,
                0f, 
                Animation.RELATIVE_TO_PARENT,
                1f, 
                Animation.RELATIVE_TO_SELF,
                0f);
        anim.setDuration(SHORT);
        anim.setInterpolator(new DecelerateInterpolator(1f));
        ret.addAnimation(anim);
        return ret;
    }

    public static Animation makePopupOutAnim() {
    	return makePopupOutAnim(true);
    }
    
    public static Animation makePopupOutAnim(boolean withAlpha) {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        if (withAlpha) {
	        // Create a fade out alpha animation
	        anim = new AlphaAnimation(1f, 0f);
	        anim.setDuration(SHORT);
	        anim.setInterpolator(new LinearInterpolator());
	        ret.addAnimation(anim);
        }
        // Create a bottom push down animation
        anim = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0f, 
                Animation.RELATIVE_TO_SELF,
                0f, 
                Animation.RELATIVE_TO_SELF,
                0f, 
                Animation.RELATIVE_TO_PARENT,
                1f);
        anim.setDuration(SHORT);
        anim.setInterpolator(new AccelerateInterpolator(1f));
        ret.addAnimation(anim);
        return ret;
    }
    
    public static Animation makeShakeAnim(int shakeWidth) {
          Animation anim;
          // Create a right-left slide in animation
          anim = new TranslateAnimation(
                  Animation.ABSOLUTE,
                  0, 
                  Animation.ABSOLUTE,
                  shakeWidth, 
                  Animation.RELATIVE_TO_SELF,
                  0f, 
                  Animation.RELATIVE_TO_SELF,
                  0f);
          anim.setDuration(LONG);
          anim.setInterpolator(new CycleInterpolator(7));
          return anim;
    }

    public static Animation makeFadeInAnim() {
        Animation anim;
        // Create a fade in alpha animation
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(SHORT);
        anim.setInterpolator(new AccelerateInterpolator());
        return anim;
    }

    public static Animation makeFadeOutAnim() {
        Animation anim;
        // Create a fade in alpha animation
        anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(SHORT);
        anim.setInterpolator(new AccelerateInterpolator());
        return anim;
    }

    public static Animation makeIconFadeInAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(true);
        // Create a fade in alpha animation
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(SHORT);
        ret.addAnimation(anim);
        // Create a scale in animation
        anim = new ScaleAnimation(
                0.8f, 
                1, 
                0.8f, 
                1, 
                Animation.RELATIVE_TO_SELF,
                0.5f, 
                Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(FLASH);
        ret.addAnimation(anim);

        ret.setInterpolator(new DecelerateInterpolator());
        
        return ret;
    }

    public static Animation makeRotateAnim(boolean clockwise) {
    	Animation anim;
    	anim = new RotateAnimation(
    			0, 
    			clockwise ? 360 : -360, 
    			RotateAnimation.RELATIVE_TO_SELF,
    			0.5f, 
    			RotateAnimation.RELATIVE_TO_SELF,
    			0.5f);
        anim.setDuration(LONG);
        anim.setRepeatCount(Animation.INFINITE);
        anim.setInterpolator(new LinearInterpolator());
        return anim;
    }

    public static Animation makeZoomInNearAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // Create a fade in alpha animation
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new LinearInterpolator());
        ret.addAnimation(anim);
        // Create a zoom in animation
        anim = new ScaleAnimation(
                0, 
                1, 
                0, 
                1, 
                Animation.RELATIVE_TO_SELF,
                0.5f, 
                Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        return ret;
    }
        
    public static Animation makeZoomInAwayAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // Create a fade out alpha animation
        anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        // Create a zoom in animation
        anim = new ScaleAnimation(
                1, 
                3, 
                1, 
                3, 
                Animation.RELATIVE_TO_SELF,
                0.5f, 
                Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        return ret;
    }
    
    public static Animation makeZoomOutNearAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // Create a fade in alpha animation
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new LinearInterpolator());
        ret.addAnimation(anim);
        // Create a zoom out animation
        anim = new ScaleAnimation(
                3, 
                1, 
                3, 
                1, 
                Animation.RELATIVE_TO_SELF,
                0.5f, 
                Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        return ret;
    }
    
    public static Animation makeZoomOutAwayAnim() {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // Create a fade out alpha animation
        anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        // Create a zoom out animation
        anim = new ScaleAnimation(
                1, 
                0, 
                1, 
                0, 
                Animation.RELATIVE_TO_SELF,
                0.5f, 
                Animation.RELATIVE_TO_SELF,
                0.5f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        return ret;
    }
    
    public static Animation makePanInAnim(float degree) {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // Create a fade in alpha animation
        anim = new AlphaAnimation(0f, 1f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new LinearInterpolator());
        ret.addAnimation(anim);
        // Create a pan in animation
        final float pivotX = (float)(1 - Math.cos(degree)) / 2;
        final float pivotY = (float)(1 + Math.sin(degree)) / 2;
        
        anim = new ScaleAnimation(
                0.8f, 
                1, 
                0.8f, 
                1, 
                Animation.RELATIVE_TO_SELF,
                pivotX, 
                Animation.RELATIVE_TO_SELF,
                pivotY);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        
        return ret;
    }

    public static Animation makePanOutAnim(float degree) {
        AnimationSet ret;
        Animation anim;
        ret = new AnimationSet(false);
        // Create a fade out alpha animation
        anim = new AlphaAnimation(1f, 0f);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        // Create a pan out animation
        final float pivotX = (float)(1 + Math.cos(degree)) / 2;
        final float pivotY = (float)(1 - Math.sin(degree)) / 2;
        anim = new ScaleAnimation(
                1, 
                0.8f, 
                1, 
                0.8f, 
                Animation.RELATIVE_TO_SELF,
                pivotX, 
                Animation.RELATIVE_TO_SELF,
                pivotY);
        anim.setDuration(MEDIUM);
        anim.setInterpolator(new DecelerateInterpolator());
        ret.addAnimation(anim);
        
        return ret;
    }

    public static LayoutAnimationController makeListLayoutAnim() {
        LayoutAnimationController ret;
        Animation anim = makeSlideInAnim(false, true, false);
        ret = new LayoutAnimationController(anim, 0.2f);
        ret.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return ret;
    }
    
	//==========================================================================
	// Inner/Nested Classes
	//==========================================================================
}
