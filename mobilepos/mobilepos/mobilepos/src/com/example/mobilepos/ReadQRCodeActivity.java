package com.example.mobilepos;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
 
public class ReadQRCodeActivity extends Activity {
 
    private Button btnScan;
    private TextView txtResult;
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
 
        //connect btnScan and txtResult to View
        btnScan = (Button)findViewById(R.id.buttonScan);
        txtResult = (TextView)findViewById(R.id.txtMemberID);
 
        //define Event when press btnScan
        btnScan.setOnClickListener(new OnClickListener() {
 
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {
                    //define intent to call Barcode Scanner
                    Intent intent = new Intent("com.google.zxing.client.android.SCAN");
                    //send Mode to Scan with Barcode Scanner
                    intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
                    //start Activity from intent which define by requestCode = 0
                    startActivityForResult(intent, 0);
                } catch (Exception e) {
                    // TODO: handle exception
                    //if don't install Barcode Scanner there will push a message Please Install Barcode Scanner
                    Toast.makeText(getBaseContext(),"Please Install Barcode Scanner",Toast.LENGTH_SHORT).show();
                }
 
            }
        });
    }
 
    //when complete the scan it will call function onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        if (requestCode == 0) //test requestCode and Barcode Scanner are same with value that we got
        {
            if (resultCode == RESULT_OK) //if Barcode Scanner complete
            {
                    //Receive Barcode Scanner that got by scan
                    String contents = intent.getStringExtra("SCAN_RESULT");
                    //Receieve Barcode Scanner that got each type
                    String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
                    //show in txtResult
                    txtResult.setText("Result : " + contents);
            }
        }
    }
}