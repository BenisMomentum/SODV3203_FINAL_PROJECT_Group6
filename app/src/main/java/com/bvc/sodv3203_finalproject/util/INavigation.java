package com.bvc.sodv3203_finalproject.util;



//Just to streamline things. This is to make sure that it is implemented.

//Please note, this is not the ideal way to implement this...but it works for our purposes.

import android.view.View;

//I don't want to have to refactor all of this.
public interface INavigation {
    void navigateTo(Class<?> activityClass);
}
