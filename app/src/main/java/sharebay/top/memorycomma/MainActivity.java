package sharebay.top.memorycomma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.text_view) TextView tv;
    @BindString(R.string.hello) String hello;

    @BindView(R.id.btnTest)
    Button btnTest;

    @OnClick(R.id.btnTest) void submit() {
        Toast.makeText(this, hello, Toast.LENGTH_SHORT).show();
        Log.e("asdfadfa#### ", "submit: &&&&&&&&&&&&&&&&$$$$$$$$########" );
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // 注意顺序
        setContentView(R.layout.activity_main);
        //getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.titlebar); // 注意顺序

        init();
    }

    private void init() {
        btnTest = (Button) findViewById(R.id.btnTest);
        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str ="";
                try {
                    str = run("https://raw.github.com/square/okhttp/master/README.md");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e("ggyy", "onClick: "+str);
                Toast.makeText(MainActivity.this, ""+str, Toast.LENGTH_SHORT).show();
            }
        });

    }

    OkHttpClient client = new OkHttpClient();

    String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /*public static void main(String[] args) throws IOException {
        GetExample example = new GetExample();
        String response = example.run("https://raw.github.com/square/okhttp/master/README.md");
        System.out.println(response);
    }*/

}
