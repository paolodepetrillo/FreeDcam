package com.troop.freedcam.ui.menu.childs;

import android.content.Context;
import android.util.AttributeSet;

import com.troop.androiddng.MainActivity;
import com.troop.freedcam.i_camera.AbstractCameraUiWrapper;
import com.troop.freedcam.i_camera.parameters.I_ModeParameter;
import com.troop.freedcam.ui.AppSettingsManager;
import com.troop.freedcam.ui.MainActivity_v2;

import java.util.ArrayList;

/**
 * Created by troop on 13.12.2014.
 */
public class ConnectSonyExpandableChild extends ExpandableChild implements I_ModeParameter
{
    MainActivity_v2 context;
    public ConnectSonyExpandableChild(MainActivity_v2 context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public ConnectSonyExpandableChild(MainActivity_v2 context) {
        super(context);
        this.context = context;
    }

    public ConnectSonyExpandableChild(MainActivity_v2 context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    public void setParameterHolder(I_ModeParameter parameterHolder, AppSettingsManager appSettingsManager, String settingsname, ArrayList<String> modulesToShow, AbstractCameraUiWrapper cameraUiWrapper) {
        this.parameterHolder = this;
        this.appSettingsManager = appSettingsManager;
        this.settingsname = settingsname;
        this.cameraUiWrapper = cameraUiWrapper;
        nameTextView.setText("Connect to Sony");
        valueTextView.setText(Value());

    }

    @Override
    public void setValue(String value)
    {
        if (value.equals("true"))
            appSettingsManager.setSonyCam(true);
        else
            appSettingsManager.setSonyCam(false);

        valueTextView.setText(value);
        context.ActivateSonyApi(value);
    }

    @Override
    public String Value()
    {
        String ret = appSettingsManager.getString(settingsname);
        if (ret.equals(""))
            ret = "false";
        return ret;
    }

    @Override
    public boolean IsSupported() {
        return true;
    }

    @Override
    public void SetValue(String valueToSet, boolean setToCamera) {


    }

    @Override
    public String GetValue() {
        return null;
    }

    @Override
    public String[] GetValues() {
        return new String[] {"true", "false"};
    }

    @Override
    public String getName() {
        return "sonyconnect";
    }
}
