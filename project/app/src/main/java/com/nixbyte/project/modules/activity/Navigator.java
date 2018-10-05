package com.nixbyte.project.modules.activity;

import android.os.Bundle;

/**
 * Created by nixbyte on 26.01.17.
 */

public interface Navigator {
    void onNext(Bundle args, Class<?> nextFragmentClass, boolean addToBackStack);
    void onBack();
}
