package com.example.peethr.wsbtest.models.alerts;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.peethr.wsbtest.models.data.weather.Globals;

public class NoInternetDialogFragment extends DialogFragment {

    Globals g = Globals.getInstance();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Context context = getActivity();

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setTitle("Problem z połączeniem")
                .setMessage("Wykryto brak połączenia z internetem. Upewnij się, że internet jest włączony")
                .setPositiveButton("Kontynuuj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        g.setContinueWithoutWeatherData(true);
                    }
                })
                .setNegativeButton("Spróbuj ponownie", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        g.setTryConnectingToDarkSky(true);
                    }
                });

        AlertDialog dialog = builder.create();
        return dialog;
    }
}
