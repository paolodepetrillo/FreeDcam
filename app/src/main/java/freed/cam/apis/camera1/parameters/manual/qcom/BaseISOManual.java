/*
 *
 *     Copyright (C) 2015 Ingo Fuchs
 *     This program is free software; you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation; either version 2 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License along
 *     with this program; if not, write to the Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * /
 */

package freed.cam.apis.camera1.parameters.manual.qcom;

import android.hardware.Camera.Parameters;

import java.util.ArrayList;

import freed.cam.apis.KEYS;
import freed.cam.apis.basecamera.CameraWrapperInterface;
import freed.cam.apis.camera1.parameters.ParametersHandler;
import freed.cam.apis.camera1.parameters.manual.BaseManualParameter;

/**
 * Created by GeorgeKiarie on 6/2/2016.
 */
public class BaseISOManual extends BaseManualParameter {

    private String cur_iso_mode = KEYS.AUTO;

    public BaseISOManual(Parameters parameters, String value, int min, int max
            , CameraWrapperInterface cameraUiWrapper, float step) {
        super(parameters, value, "", "", cameraUiWrapper, step);
        isSupported = true;
        isVisible = true;
        stringvalues = createStringArray(min,max,step);
    }

    @Override
    public int GetValue() {
        return currentInt;
    }

    @Override
    public void SetValue(int valueToSet) {
        currentInt = valueToSet;
        //set to auto
        if (currentInt == 0) {
            set_to_auto();
        } else //set manual wb mode and key_value
        {
            set_manual();
        }
        ((ParametersHandler) cameraUiWrapper.GetParameterHandler()).SetParametersToCamera(parameters);
    }


    protected void set_manual()
    {
        cur_iso_mode = cameraUiWrapper.GetParameterHandler().IsoMode.GetValue();

        if (!cameraUiWrapper.GetParameterHandler().IsoMode.GetValue().equals(KEYS.ISO_MANUAL))
            cameraUiWrapper.GetParameterHandler().IsoMode.SetValue(KEYS.ISO_MANUAL, true);
        parameters.set(key_value, stringvalues[currentInt]);


    }

    protected void set_to_auto()
    {
        if (cameraUiWrapper.GetParameterHandler().IsoMode.GetValue().equals(KEYS.ISO_MANUAL))
            cameraUiWrapper.GetParameterHandler().IsoMode.SetValue(KEYS.AUTO, true);
        cameraUiWrapper.GetParameterHandler().IsoMode.SetValue(cur_iso_mode, true);

    }


    @Override
    protected String[] createStringArray(int min, int max, float step)
    {
        ArrayList<String> t = new ArrayList<>();
        t.add(KEYS.AUTO);
        for (int i = min; i<=max;i+=step)
        {
            t.add(i+"");
        }
        stringvalues = new String[t.size()];
        t.toArray(stringvalues);
        return stringvalues;
    }
}
