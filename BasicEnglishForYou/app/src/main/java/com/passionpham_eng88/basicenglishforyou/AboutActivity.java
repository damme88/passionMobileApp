package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class AboutActivity extends AppCompatActivity {

    RelativeLayout about_screen;
    EditText edtInfoText;
    Button btnBack;

    protected void MessageMap()

    {
        btnBack = (Button)findViewById(R.id.btnExit);
        edtInfoText = (EditText)findViewById(R.id.editInfoText);
        about_screen = (RelativeLayout)findViewById(R.id.aboutScreen);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        MessageMap();
        edtInfoText.setFocusable(false);
        Bundle extras = getIntent().getExtras();
        int type = -1;
        if (extras != null) {
            type  = extras.getInt("Type");
        }

        if (type == 0)
        {
            edtInfoText.setText("ENote là một ứng dụng sổ tay tiếng anh.\n" +
                    "ENote giúp bạn học tiếng anh lúc nhàn rỗi.\n" +
                    "ENote có nhiều các mẫu câu trong các tình huống thực tế.\n" +
                    "ENote tóm tắt các mẫu ngữ pháp thông dụng và cơ bản.\n"+
                    "ENote có số lượng bài hội thoại đa dạng phong phú.\n" +
                    "ENote có một số bài mẫu dành cho trẻ em.\n"+
                    "Sản phẩm thuộc sỡ hữu của blog: Phattrienphanmem123az.com");
        }
        else if (type == 1)
        {
            edtInfoText.setText("Đây là những bài nghe tiếng anh cho trẻ em.\n" +
                    "Chọn bài trong list, sau đó chọn nút play.\n" +
                    "Hình vẽ trong thể hiện một số từ vựng trong bài.\n" +
                    "Chức năng [show content], để xem nội dụng bài học.");
        }
        //about_screen.setBackgroundResource(R.drawable.aboutbkgn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle extras = getIntent().getExtras();
                int type = -1;
                if (extras != null) {
                    type  = extras.getInt("Type");
                }
                finishAffinity();
                if (type == 0)
                {
                    Intent mainScreen = new Intent(AboutActivity.this, MainActivity.class);
                    startActivity(mainScreen);
                }
                else
                {
                    Intent kidEng = new Intent(AboutActivity.this, EnglishForKidActivity.class);
                    startActivity(kidEng);
                }
            }
        });
    }
}
