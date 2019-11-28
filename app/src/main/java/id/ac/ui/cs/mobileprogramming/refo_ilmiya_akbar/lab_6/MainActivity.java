package id.ac.ui.cs.mobileprogramming.refo_ilmiya_akbar.lab_6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText editTextNumberA, editTextNumberB;
    private TextView result;
    private Button enableButton, disableButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumberA = findViewById(R.id.numberA);
        editTextNumberB = findViewById(R.id.numberB);
        Button calculate = findViewById(R.id.calculate);
        result = findViewById(R.id.result);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextNumberA.getText().toString().equals("")) {
                    editTextNumberA.requestFocus();
                    editTextNumberA.setError("Fill this field");
                }
                if (editTextNumberB.getText().toString().equals("")) {
                    editTextNumberB.requestFocus();
                    editTextNumberB.setError("Fill this field");
                }
                String numberA = editTextNumberA.getText().toString();
                String numberB = editTextNumberB.getText().toString();
                if (!numberA.equals("") && !numberB.equals("")) {
                    result.setText("Calculated from JNI: " +
                            stringFromJNI(Integer.parseInt(numberA), Integer.parseInt(numberB)));
                }
            }
        });

        enableButton = (Button) findViewById(R.id.enableButton);
        disableButton = (Button) findViewById(R.id.disableButton);

        enableButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(true);
                WifiConfiguration wifiConfig = new WifiConfiguration();
                String ssid = "fiersabesari";
                String key = "aaaaaaaaaa";
                wifiConfig.SSID = String.format("\"%s\"", ssid);
                wifiConfig.preSharedKey = String.format("\"%s\"", key);

                WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(WIFI_SERVICE);
                int netId = wifiManager.addNetwork(wifiConfig);
                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
            }
        });

        disableButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifi.setWifiEnabled(false);
            }
        });
    }

    public native int stringFromJNI(int numberA, int numberB);

    static {
        System.loadLibrary("helloworld");
    }
}
