/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.support.test.espresso.contrib;

import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckPreset;
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResult.AccessibilityCheckResultDescriptor;
import com.google.android.apps.common.testing.accessibility.framework.integrations.espresso.AccessibilityValidator;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.util.HumanReadables;

import android.util.Log;
import android.view.View;

/**
 * A class to enable automated accessibility checks in Espresso tests. These checks will run
 * as a global {@code ViewAssertion}, and cover a variety of accessibility issues
 * (see {@link AccessibilityCheckPreset#LATEST} to see which checks are run).
 */
public final class AccessibilityChecks {

  private static final AccessibilityValidator CHECK_EXECUTOR =
      new AccessibilityValidator().setResultDescriptor(
          new AccessibilityCheckResultDescriptor() {
            @Override
            public String describeView(View view) {
              return HumanReadables.describe(view);
            }
          });

  private static final ViewAssertion ACCESSIBILITY_CHECK_ASSERTION = new ViewAssertion() {
    @Override
    public void check(View view, NoMatchingViewException noViewFoundException) {
      if (noViewFoundException != null) {
        Log.e(TAG,
            String.format("'accessibility checks could not be performed because view '%s' was not"
                + "found.\n", noViewFoundException.getViewMatcherDescription()));
        throw noViewFoundException;
      }
      if (view == null) {
        throw new NullPointerException();
      }
      CHECK_EXECUTOR.checkAndReturnResults(view);
    }
  };

  private static boolean checksEnabled = false;
  private static final String TAG = "AccessibilityChecks";

  private AccessibilityChecks() {
  }

  /**
   * Enables accessibility checking as a global ViewAssertion in {@link ViewActions}.
   *
   * @return the backing {@link AccessibilityValidator}, on which options for check
   *         execution can be set
   */
  public static AccessibilityValidator enable() {
    Checks.checkState(!checksEnabled, "Accessibility checks already enabled!");
    checksEnabled = true;
    ViewActions.addGlobalAssertion("Accessibility Checks", ACCESSIBILITY_CHECK_ASSERTION);
    return CHECK_EXECUTOR;
  }

  /**
   * @return the backing {@link ViewAssertion} that can be used to explicitly check accessibility
   */
  public static ViewAssertion accessibilityAssertion() {
    return ACCESSIBILITY_CHECK_ASSERTION;
  }
}
