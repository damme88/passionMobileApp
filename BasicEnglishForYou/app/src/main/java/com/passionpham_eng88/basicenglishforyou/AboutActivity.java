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
            edtInfoText.setText("English Notebook là một ứng dụng " +
                    "giúp bạn tra cứu nhanh những nhóm từ vựng " +
                    "trong các chủ đề thông dụng hàng ngày như: \n" +
                    "+ Nhóm từ về đồ vật trong nhà. \n" +
                    "+ Nhóm từ về đồ ăn thực phẩm \n" +
                    "+ Nhóm từ về nghề nghiệp...\n" +
                    "Bên cạnh đó. Bạn cũng có thể tra cứu những " +
                    "mẫu câu tiếng anh thường sử dụng trong các " +
                    "tình huống thực tế ngoài đời như: \n" +
                    "+ Khi đi du lịch, gặp gỡ lần đầu tiên \n" +
                    "+ Khi đi mua hàng, khi cần sự trợ giúp...\n" +
                    "English Notebook tóm tắt các mẫu ngữ pháp thông dụng và cơ bản.\n"+
                    "English Notebook có một số lượng bài nghe cơ bản " +
                    "và ngắn gọn giúp bạn luyện nghe mỗi ngày.\n" +
                    "Ngoài ra một phần nhỏ gồm các bài học tiếng anh " +
                    "dành cho trẻ em, với hình vẽ minh họa ngộ nghĩnh. " +
                    "Có thể giúp bé học tiếng anh như một trò chơi đố vui.\n" +
                    "Mọi ý kiến đóng góp tích cực xin vui lòng gửi về email: " +
                    "Damme88@gmail.com. " +
                    "Xin cảm ơn. ");
        }
        else if (type == 1)
        {
            edtInfoText.setText("Đây là những bài nghe tiếng anh cho trẻ em\n" +
                    "Chọn bài trong list, sau đó chọn nút play.\n" +
                    "Những hình vẽ trong mỗi bài không phải là mô tả nội dung bài,\n" +
                    "mà đó là những hình vẽ thể hiện một số từ vựng trong bài.\n" +
                    "Bạn có thể cho bé nghe và nhận biết hình minh họa là từ vựng nào trong bài.\n" +
                    "Bạn có chọn chức năng [Hiện nội dung], để xem nội dụng bài học.");
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
