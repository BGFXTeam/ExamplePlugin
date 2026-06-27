package com.example.bgfx.plugin;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.executor.plugin.Plugin;
import com.executor.plugin.PluginContext;
import com.executor.bgfxui.ScriptManager;

public class ExampePlugin extends Plugin {

    WindowManager wm;

    View icon;
    View menu;

    WindowManager.LayoutParams iconParams;
    WindowManager.LayoutParams menuParams;

    @Override
    public void onLoad(PluginContext ctx) {

        final Context context = ctx.getContext();
        

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);


        // Floating button
        TextView open = new TextView(context);
        open.setText("≡");
        open.setTextSize(30);
        open.setGravity(Gravity.CENTER);
        open.setTextColor(Color.WHITE);

        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.BLACK);
        bg.setCornerRadius(100);
        open.setBackground(bg);


        icon = open;

        iconParams = new WindowManager.LayoutParams(
            120,
            120,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            -3
        );

        iconParams.gravity = Gravity.TOP | Gravity.LEFT;
        iconParams.x = 200;
        iconParams.y = 300;



        // Drag icon
        open.setOnTouchListener(new View.OnTouchListener() {

                int x,y;
                float dx,dy;

                public boolean onTouch(View v, MotionEvent e){

                    if(e.getAction()==MotionEvent.ACTION_DOWN){

                        x=iconParams.x;
                        y=iconParams.y;

                        dx=e.getRawX();
                        dy=e.getRawY();

                        return true;
                    }

                    if(e.getAction()==MotionEvent.ACTION_MOVE){

                        iconParams.x=x+(int)(e.getRawX()-dx);
                        iconParams.y=y+(int)(e.getRawY()-dy);

                        wm.updateViewLayout(icon,iconParams);

                        return true;
                    }

                    if(e.getAction()==MotionEvent.ACTION_UP){

                        if(Math.abs(e.getRawX()-dx)<10 &&
                           Math.abs(e.getRawY()-dy)<10){

                            showMenu(context);
                        }

                    }

                    return true;
                }
            });


        wm.addView(icon,iconParams);

    }



    void showMenu(final Context context){

        if(menu != null)
            return;


        LinearLayout box = new LinearLayout(context);
        box.setOrientation(LinearLayout.VERTICAL);
        box.setPadding(10,10,10,10);


        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.rgb(30,30,30));
        bg.setCornerRadius(20);

        box.setBackground(bg);



        TextView title = new TextView(context);
        title.setText("ExamplePlugin");
        title.setTextColor(Color.WHITE);
        title.setTextSize(18);
        title.setPadding(10,5,10,5);


        Button test = new Button(context);
        test.setText("Fly");

        test.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    //Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
                    ScriptManager.execute("local moveDir = VectorUtil.newVector3(0.0, 1.35, 0.0)\n"+
                    "local player = PlayerManager:getClientPlayer()\n"+
                    "player.Player:setAllowFlying(true)\n"+
                    "player.Player:setFlying(true)\n"+
                    "player.Player:moveEntity(moveDir)\n"+
                    "PlayerManager:getClientPlayer().Player:setSpeedAdditionLevel(150000)");
                }
            });



        Button close = new Button(context);
        close.setText("Close");

        close.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    if(menu != null){

                        wm.removeView(menu);
                        menu = null;

                    }

                }
            });



        box.addView(title);
        box.addView(test);
        box.addView(close);


        menu = box;


        menuParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        );


        menuParams.gravity = Gravity.CENTER;


        wm.addView(menu, menuParams);

    }
}
