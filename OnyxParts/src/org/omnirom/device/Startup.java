/*
* Copyright (C) 2013 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.omnirom.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;

public class Startup extends BroadcastReceiver {

    private void restore(String file, boolean enabled) {
        if (file == null) {
            return;
        }
        if (enabled) {
            Utils.writeValue(file, "1");
        }
    }

    private void restore(String file, String value) {
        if (file == null) {
            return;
        }
        Utils.writeValue(file, value);
    }

    private String getGestureFile(String key) {
        return GestureSettings.getGestureFile(key);
    }

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        // circle -> camera
        String mapping = GestureSettings.DEVICE_GESTURE_MAPPING_0;
        String value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.CAMERA_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        boolean enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_O_APP), enabled);

        // double swipe -> music play
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_1;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.MUSIC_PLAY_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_DOUBLE_SWIPE_APP), enabled);

        // up arrow
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_2);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_UP_ARROW_APP), enabled);

        // down arrow -> flashlight
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_3;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.TORCH_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_DOWN_ARROW_APP), enabled);

        // left arrow -> music prev
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_4;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.MUSIC_PREV_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_LEFT_ARROW_APP), enabled);

        // right arrow -> music next
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_5;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.MUSIC_NEXT_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_RIGHT_ARROW_APP), enabled);

        // double up arrow
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_6);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_W_APP), enabled);

        // double down arrow
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_7);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_M_APP), enabled);

        // down swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_8);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_DOWN_SWIPE_APP), enabled);

        // up swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_9);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_UP_SWIPE_APP), enabled);

        // left swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_10);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_LEFT_SWIPE_APP), enabled);

        // right swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_11);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_RIGHT_SWIPE_APP), enabled);

        // high brightness mode
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_HBM_SWITCH, false);
        if (enabled) {
            restore(HBMModeSwitch.getFile(), "2");
        }

        // back and recents
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_SWAP_BACK_RECENTS, true);
        if (enabled) {
            restore(SwapBackRecents.getFile(), "1");
        }

        // vibration
        VibratorStrengthPreference.restore(context);
    }
}
