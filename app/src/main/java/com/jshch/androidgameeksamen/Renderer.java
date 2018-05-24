package com.jshch.androidgameeksamen;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Renderer extends Component implements DrawAble, LoadAble {

    public Bitmap bitmap;
    int img;

    public Renderer(GameObject go, int img) {
        super(go);
        this.img = img;
        tag = "Renderer";

    }


    public void Draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, go.getTransform().GetPosition().getX(), go.getTransform().GetPosition().getY(), paint);

    }

    public Bitmap GetBitmap() {
        return bitmap;
    }

    @Override
    public void LoadContent(Resources resources) {

        bitmap = BitmapFactory.decodeResource(resources, img);
        if (GetGameObject().getTransform().scale != new Vector2(1, 1) && GetGameObject().getTransform().scale != null) {
            bitmap = ScaledBitmap(bitmap);
        }
    }

    Bitmap ScaledBitmap(Bitmap toScale) {
        // scale image
        int width = (int) (toScale.getWidth() * GetGameObject().transform.scale.getX());
        int height = (int) (toScale.getHeight() * GetGameObject().transform.scale.getY());
        if (width > 0 && height > 0) {
            Bitmap scaledMap = Bitmap.createScaledBitmap(toScale, width, height, true);
            return scaledMap;
        }
        return toScale;
    }
}
