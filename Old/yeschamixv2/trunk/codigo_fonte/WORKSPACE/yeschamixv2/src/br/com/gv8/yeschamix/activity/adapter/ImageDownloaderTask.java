package br.com.gv8.yeschamix.activity.adapter;

import java.lang.ref.WeakReference;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import br.com.gv8.yeschamix.activity.R;
import br.com.gv8.yeschamix.util.Utilidades;

public class ImageDownloaderTask extends AsyncTask<String, Void, Bitmap> {
	private final WeakReference<ImageView> imageViewReference;

	public ImageDownloaderTask(ImageView imageView) {
		imageViewReference = new WeakReference<ImageView>(imageView);
	}

	@Override
	// Actual download method, run in the task thread
	protected Bitmap doInBackground(String... params) {
		// params comes from the execute() call: params[0] is the url.
		try {
			return Utilidades.getBitmap( null , params[0] , true , 0 );
		} catch( Exception e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	// Once the image is downloaded, associates it to the imageView
	protected void onPostExecute(Bitmap bitmap) {
		if (isCancelled()) {
			bitmap = null;
		}

		if (imageViewReference != null) {
			ImageView imageView = imageViewReference.get();
			if (imageView != null) {

				if (bitmap != null) {
					imageView.setImageBitmap(bitmap);
				} else {
					imageView.setImageDrawable(imageView.getContext().getResources().getDrawable(R.drawable.indisponivel));
				}
			}

		}
	}

}