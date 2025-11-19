package com.applex.snaplingo;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import com.applex.snaplingo.LinkPreview.ApplexLinkPreviewShort;
import com.applex.snaplingo.LinkPreview.ViewListener;
import com.applex.snaplingo.util.Constants;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.actions.SearchIntents;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.WriterException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.i18n.TextBundle;

public class MainActivity extends AppCompatActivity {
    static String OCRtext = "";
    public static Uri image_uri = null;
    public static int itemID = -1;
    public static EditText mResultEt;
    public static Uri resultUri;
    Bitmap bitmap;
    private LinearLayout btnLocate;
    private LinearLayout btnSearch;
    private LinearLayout btnTranslate;
    private Button btnVisit;
    private CardView cd;
    private ConnectivityManager cm;
    private ImageButton cpy_btn;
    /* access modifiers changed from: private */
    public FloatingActionMenu flmenu;
    private LinearLayout fullView;
    private TextView imgprev;
    /* access modifiers changed from: private */
    public ApplexLinkPreviewShort linkPreviewShort;
    private AdView mAdView;
    private DatabaseHelper myDB;
    /* access modifiers changed from: private */
    public Dialog mydialogue;
    /* access modifiers changed from: private */
    public Uri path;
    private PhotoView pv;
    /* access modifiers changed from: private */
    public RelativeLayout relativeLayout;
    private int sel = 5;

    /* access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolb));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        this.mAdView = (AdView) findViewById(R.id.ad_view);
        this.mAdView.loadAd(new AdRequest.Builder().build());
        this.cm = (ConnectivityManager) getSystemService("connectivity");
        this.mydialogue = new Dialog(this);
        this.myDB = new DatabaseHelper(this);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.rel_main);
        this.btnLocate = (LinearLayout) findViewById(R.id.button1);
        this.btnTranslate = (LinearLayout) findViewById(R.id.button2);
        this.btnSearch = (LinearLayout) findViewById(R.id.button3);
        this.btnVisit = (Button) findViewById(R.id.visitButton);
        this.cpy_btn = (ImageButton) findViewById(R.id.etbtn_copy);
        this.fullView = (LinearLayout) findViewById(R.id.expand);
        this.linkPreviewShort = (ApplexLinkPreviewShort) findViewById(R.id.link_preview);
        this.cd = (CardView) findViewById(R.id.card);
        this.imgprev = (TextView) findViewById(R.id.imgprev);
        mResultEt = (EditText) findViewById(R.id.resultEt);
        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        this.pv = photoView;
        photoView.setZoomable(true);
        this.pv.setAdjustViewBounds(Boolean.FALSE.booleanValue());
        this.pv.setFitsSystemWindows(Boolean.TRUE.booleanValue());
        if (this.pv.getImageMatrix() == null) {
            this.imgprev.setVisibility(8);
            this.pv.setVisibility(8);
        } else {
            this.imgprev.setVisibility(0);
            this.pv.setVisibility(0);
        }
        FloatingActionMenu floatingActionMenu = (FloatingActionMenu) findViewById(R.id.menu);
        this.flmenu = floatingActionMenu;
        floatingActionMenu.close(true);
        ((FloatingActionButton) findViewById(R.id.doc)).setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass1 */

            public void onClick(View view) {
                if (MainActivity.mResultEt.length() != 0) {
                    MainActivity.this.databaseAdder(0);
                    MainActivity.this.saveDoc();
                    return;
                }
                Toast.makeText(MainActivity.this, "Field empty...", 0).show();
            }
        });
        ((FloatingActionButton) findViewById(R.id.pdf)).setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass2 */

            public void onClick(View view) {
                if (MainActivity.mResultEt.length() != 0) {
                    MainActivity.this.databaseAdder(0);
                    MainActivity.this.savePdf();
                    return;
                }
                Toast.makeText(MainActivity.this, "Field empty...", 0).show();
            }
        });
        ((FloatingActionButton) findViewById(R.id.qr)).setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass3 */

            public void onClick(View view) {
                if (MainActivity.mResultEt.length() != 0) {
                    MainActivity.this.GenerateQR(MainActivity.mResultEt.getText().toString());
                } else {
                    Toast.makeText(MainActivity.this, "Field empty...", 0).show();
                }
            }
        });
        this.btnLocate.setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass4 */

            public void onClick(View view) {
                if (MainActivity.mResultEt.length() != 0) {
                    MainActivity.this.databaseAdder(0);
                    MainActivity.this.Locate();
                    return;
                }
                Toast.makeText(MainActivity.this, "Field empty...", 0).show();
            }
        });
        this.btnTranslate.setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass5 */

            public void onClick(View view) {
                if (MainActivity.mResultEt.length() != 0) {
                    MainActivity.this.databaseAdder(0);
                    MainActivity.this.Translate();
                    return;
                }
                Toast.makeText(MainActivity.this, "Field empty...", 0).show();
            }
        });
        this.btnSearch.setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass6 */

            public void onClick(View view) {
                if (MainActivity.mResultEt.length() != 0) {
                    MainActivity.this.databaseAdder(0);
                    MainActivity.this.Search();
                    return;
                }
                Toast.makeText(MainActivity.this, "Field empty...", 0).show();
            }
        });
        this.cpy_btn.setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass7 */

            public void onClick(View view) {
                if (MainActivity.mResultEt.length() != 0) {
                    MainActivity.this.databaseAdder(0);
                    ((ClipboardManager) MainActivity.this.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied", MainActivity.mResultEt.getText().toString().replaceAll("\n", " ")));
                    Toast.makeText(MainActivity.this, "Copied to clipboard", 0).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Field empty...", 0).show();
            }
        });
        this.fullView.setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass8 */

            public void onClick(View view) {
                MainActivity.this.flmenu.close(true);
                Intent intent = new Intent(MainActivity.this, DocView.class);
                ActivityOptionsCompat makeSceneTransitionAnimation = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this, MainActivity.mResultEt, ViewCompat.getTransitionName(MainActivity.mResultEt));
                intent.putExtra("OCRtext", MainActivity.mResultEt.getText().toString());
                MainActivity.this.startActivity(intent, makeSceneTransitionAnimation.toBundle());
            }
        });
        this.pv.setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass9 */

            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Tap & hold to crop", 0).show();
            }
        });
        this.pv.setOnLongClickListener(new View.OnLongClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass10 */

            public boolean onLongClick(View view) {
                CropImage.activity(MainActivity.image_uri).setActivityTitle("SnapCrop").setCropMenuCropButtonTitle("Set").setAllowRotation(Boolean.TRUE.booleanValue()).setAllowCounterRotation(Boolean.TRUE.booleanValue()).setAllowFlipping(Boolean.TRUE.booleanValue()).setAutoZoomEnabled(Boolean.TRUE.booleanValue()).setMultiTouchEnabled(Boolean.FALSE.booleanValue()).setGuidelines(CropImageView.Guidelines.ON).start(MainActivity.this);
                return true;
            }
        });
        Intent intent = getIntent();
        if (intent.getStringExtra("selection") != null) {
            this.sel = Integer.parseInt(intent.getStringExtra("selection"));
        }
        String action = intent.getAction();
        String type = intent.getType();
        if (!"android.intent.action.SEND".equals(action) || type == null) {
            int i = this.sel;
            if (i == 3) {
                this.btnVisit.setVisibility(8);
                this.pv.setVisibility(8);
                this.imgprev.setVisibility(8);
                mResultEt.setMinHeight(CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA);
                mResultEt.setText(getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME));
                OCRtext = mResultEt.getText().toString();
                if (mResultEt.length() != 0) {
                    if (mResultEt.getUrls().length > 0) {
                        String url = mResultEt.getUrls()[0].getURL();
                        if (url.contains("http")) {
                            this.linkPreviewShort.setVisibility(0);
                            this.linkPreviewShort.setLink(url, new ViewListener() {
                                /* class com.applex.snaplingo.MainActivity.AnonymousClass12 */

                                @Override // com.applex.snaplingo.LinkPreview.ViewListener
                                public void onSuccess(boolean z) {
                                }

                                @Override // com.applex.snaplingo.LinkPreview.ViewListener
                                public void onError(Exception exc) {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        /* class com.applex.snaplingo.MainActivity.AnonymousClass12.AnonymousClass1 */

                                        public void run() {
                                            MainActivity.this.linkPreviewShort.setVisibility(8);
                                        }
                                    });
                                }
                            });
                        }
                    }
                    databaseAdder(0);
                }
            } else if (i == 2) {
                this.pv.setVisibility(8);
                this.imgprev.setVisibility(8);
                mResultEt.setMinHeight(CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA);
                final String stringExtra = getIntent().getStringExtra(TextBundle.TEXT_ENTRY);
                mResultEt.setText(stringExtra);
                if (mResultEt.length() != 0) {
                    if (mResultEt.getUrls().length > 0) {
                        String url2 = mResultEt.getUrls()[0].getURL();
                        if (url2.contains("http")) {
                            this.btnVisit.setVisibility(0);
                            this.linkPreviewShort.setVisibility(0);
                            this.linkPreviewShort.setLink(url2, new ViewListener() {
                                /* class com.applex.snaplingo.MainActivity.AnonymousClass13 */

                                @Override // com.applex.snaplingo.LinkPreview.ViewListener
                                public void onSuccess(boolean z) {
                                }

                                @Override // com.applex.snaplingo.LinkPreview.ViewListener
                                public void onError(Exception exc) {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        /* class com.applex.snaplingo.MainActivity.AnonymousClass13.AnonymousClass1 */

                                        public void run() {
                                            MainActivity.this.linkPreviewShort.setVisibility(8);
                                        }
                                    });
                                }
                            });
                        }
                    }
                    databaseAdder(0);
                }
                this.btnVisit.setOnClickListener(new View.OnClickListener() {
                    /* class com.applex.snaplingo.MainActivity.AnonymousClass14 */

                    public void onClick(View view) {
                        MainActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(stringExtra)));
                    }
                });
            } else if (i == 1) {
                this.btnVisit.setVisibility(8);
                this.pv.setVisibility(0);
                this.imgprev.setVisibility(0);
                this.pv.setImageURI(resultUri);
                String stringExtra2 = getIntent().getStringExtra("Text");
                OCRtext = stringExtra2;
                mResultEt.setText(stringExtra2);
                if (mResultEt.getUrls().length > 0) {
                    String url3 = mResultEt.getUrls()[0].getURL();
                    if (url3.contains("http")) {
                        this.linkPreviewShort.setVisibility(0);
                        this.linkPreviewShort.setLink(url3, new ViewListener() {
                            /* class com.applex.snaplingo.MainActivity.AnonymousClass15 */

                            @Override // com.applex.snaplingo.LinkPreview.ViewListener
                            public void onSuccess(boolean z) {
                            }

                            @Override // com.applex.snaplingo.LinkPreview.ViewListener
                            public void onError(Exception exc) {
                                new Handler(Looper.getMainLooper()).post(new Runnable() {
                                    /* class com.applex.snaplingo.MainActivity.AnonymousClass15.AnonymousClass1 */

                                    public void run() {
                                        MainActivity.this.linkPreviewShort.setVisibility(8);
                                    }
                                });
                            }
                        });
                    }
                }
            } else if (i == 5) {
                mResultEt.setHint("Start typing...");
                this.pv.setVisibility(8);
                this.imgprev.setVisibility(8);
                this.btnVisit.setVisibility(8);
                mResultEt.setMinHeight(CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA);
            } else if (i == 6) {
                this.btnVisit.setVisibility(8);
                if (intent.getStringExtra("path") != null) {
                    Uri fromFile = Uri.fromFile(new File(getIntent().getStringExtra("path")));
                    image_uri = fromFile;
                    if (fromFile != null) {
                        CropImage.activity(fromFile).setActivityTitle("SnapCrop").setCropMenuCropButtonTitle("Set").setAllowRotation(Boolean.TRUE.booleanValue()).setAllowCounterRotation(Boolean.TRUE.booleanValue()).setAllowFlipping(Boolean.TRUE.booleanValue()).setAutoZoomEnabled(Boolean.TRUE.booleanValue()).setMultiTouchEnabled(Boolean.FALSE.booleanValue()).setGuidelines(CropImageView.Guidelines.ON).start(this);
                        return;
                    }
                    return;
                }
                Toast.makeText(getApplicationContext(), "Failed", 0).show();
            }
        } else {
            this.btnVisit.setVisibility(8);
            this.pv.setVisibility(0);
            this.imgprev.setVisibility(0);
            if ("text/plain".equals(type)) {
                String stringExtra3 = intent.getStringExtra("android.intent.extra.TEXT");
                if (stringExtra3 != null) {
                    mResultEt.setText(stringExtra3);
                    if (mResultEt.getUrls().length > 0) {
                        String url4 = mResultEt.getUrls()[0].getURL();
                        if (url4.contains("http")) {
                            this.linkPreviewShort.setVisibility(0);
                            this.linkPreviewShort.setLink(url4, new ViewListener() {
                                /* class com.applex.snaplingo.MainActivity.AnonymousClass11 */

                                @Override // com.applex.snaplingo.LinkPreview.ViewListener
                                public void onSuccess(boolean z) {
                                }

                                @Override // com.applex.snaplingo.LinkPreview.ViewListener
                                public void onError(Exception exc) {
                                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                                        /* class com.applex.snaplingo.MainActivity.AnonymousClass11.AnonymousClass1 */

                                        public void run() {
                                            MainActivity.this.linkPreviewShort.setVisibility(8);
                                        }
                                    });
                                }
                            });
                        }
                    }
                    OCRtext = mResultEt.getText().toString();
                    databaseAdder(0);
                }
            } else if (type.startsWith("image/")) {
                Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
                image_uri = uri;
                if (uri != null) {
                    CropImage.activity(uri).setActivityTitle("SnapCrop").setCropMenuCropButtonTitle("Set").setAllowRotation(Boolean.TRUE.booleanValue()).setAllowCounterRotation(Boolean.TRUE.booleanValue()).setAllowFlipping(Boolean.TRUE.booleanValue()).setAutoZoomEnabled(Boolean.TRUE.booleanValue()).setMultiTouchEnabled(Boolean.FALSE.booleanValue()).setGuidelines(CropImageView.Guidelines.ON).start(this);
                }
            }
        }
    }

    public void Search() {
        if (this.cm.getActiveNetworkInfo() == null) {
            Toast.makeText(this, "Please check your internet connection and try again...", 0).show();
        } else if (mResultEt.length() != 0) {
            try {
                Intent intent = new Intent("android.intent.action.WEB_SEARCH");
                intent.putExtra(SearchIntents.EXTRA_QUERY, mResultEt.getText().toString());
                startActivity(intent);
            } catch (Exception unused) {
            }
        } else {
            Toast.makeText(this, "No text found :(", 0).show();
        }
    }

    public void Translate() {
        if (this.cm.getActiveNetworkInfo() == null) {
            Toast.makeText(this, "Please check your internet connection and try again...", 0).show();
        } else if (mResultEt.length() != 0) {
            ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copied", mResultEt.getText().toString()));
            String replaceAll = mResultEt.getText().toString().replaceAll(" ", "+");
            Intent intent = new Intent(this, WebViewTranslate.class);
            intent.putExtra("bool", ExifInterface.GPS_MEASUREMENT_3D);
            intent.putExtra(TextBundle.TEXT_ENTRY, replaceAll);
            startActivity(intent);
        } else {
            Toast.makeText(this, "No text found :(", 0).show();
        }
    }

    public void Locate() {
        if (this.cm.getActiveNetworkInfo() == null) {
            Toast.makeText(this, "Please check your internet connection and try again...", 0).show();
        } else if (mResultEt.length() != 0) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("geo:0,0?z=15&q=" + Uri.encode(mResultEt.getText().toString())));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        } else {
            Toast.makeText(this, "Field Empty", 0).show();
        }
    }

    private void saveFile(final String fileType) {
        String dialogExtensionText;
        String directory;
        final String extension;
        final String snackbarMessage;
        final String shareType;

        if (fileType.equals("pdf")) {
            dialogExtensionText = getString(R.string.pdf);
            directory = "Pdf";
            extension = Constants.pdfExtension;
            snackbarMessage = "PDF has been saved";
            shareType = "application/pdf";
        } else {
            dialogExtensionText = getString(R.string.txt);
            directory = "Text doc";
            extension = ".txt";
            snackbarMessage = "Doc. has been saved";
            shareType = "text/plain";
        }

        Dialog dialog = new Dialog(this);
        this.mydialogue = dialog;
        dialog.setContentView(R.layout.dialog_file_name);
        this.mydialogue.setCanceledOnTouchOutside(Boolean.TRUE.booleanValue());
        this.mydialogue.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ((TextView) this.mydialogue.findViewById(R.id.extension)).setText(dialogExtensionText);
        final EditText editText = (EditText) this.mydialogue.findViewById(R.id.fname);
        final String defaultFileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Long.valueOf(System.currentTimeMillis()));
        editText.setHint(defaultFileName);

        ((TextView) this.mydialogue.findViewById(R.id.save)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String fileName = editText.length() != 0 ? editText.getText().toString().trim().replaceAll(" ", "_") : defaultFileName;
                File folder = new File(Environment.getExternalStorageDirectory() + "/SnapLingo", directory);
                folder.mkdirs();
                final String filePath = folder.getAbsolutePath() + "/" + fileName + extension;
                final File file = new File(filePath);

                try {
                    if (fileType.equals("pdf")) {
                        Document document = new Document();
                        PdfWriter.getInstance(document, new FileOutputStream(file));
                        document.open();
                        document.add(new Paragraph(MainActivity.mResultEt.getText().toString().replaceAll("\n", " ")));
                        document.close();
                    } else {
                        FileOutputStream fos = new FileOutputStream(file);
                        fos.write(MainActivity.mResultEt.getText().toString().getBytes());
                        fos.close();
                    }

                    Snackbar.make(MainActivity.this.flmenu, snackbarMessage, 0).setAction("Share", new View.OnClickListener() {
                        public void onClick(View view) {
                            if (file.exists()) {
                                Uri unused = MainActivity.this.path = FileProvider.getUriForFile(MainActivity.this, Constants.AUTHORITY_APP, file);
                                Intent intent = new Intent();
                                intent.setAction("android.intent.action.SEND");
                                intent.putExtra("android.intent.extra.TEXT", "Sharing File");
                                intent.putExtra("android.intent.extra.STREAM", MainActivity.this.path);
                                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setType(shareType);
                                MainActivity.this.startActivity(Intent.createChooser(intent, "Share"));
                            } else {
                                Toast.makeText(MainActivity.this, fileName + " missing " + filePath, Toast.LENGTH_LONG).show();
                            }
                        }
                    }).show();

                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                MainActivity.this.mydialogue.dismiss();
                MainActivity.this.flmenu.close(true);
            }
        });
        this.mydialogue.show();
    }

    /* access modifiers changed from: private */
    public void savePdf() {
        saveFile("pdf");
    }

    /* access modifiers changed from: private */
    public void saveDoc() {
        saveFile("doc");
    }

    /* access modifiers changed from: private */
    public void GenerateQR(String str) {
        Dialog dialog = new Dialog(this);
        this.mydialogue = dialog;
        dialog.setContentView(R.layout.qrdialog);
        this.mydialogue.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        ImageView imageView = (ImageView) this.mydialogue.findViewById(R.id.qrImageview);
        ImageView imageView2 = (ImageView) this.mydialogue.findViewById(R.id.shareQr);
        Display defaultDisplay = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        defaultDisplay.getSize(point);
        int i = point.x;
        int i2 = point.y;
        if (i >= i2) {
            i = i2;
        }
        try {
            Bitmap encodeAsBitmap = new QRGEncoder(str, null, QRGContents.Type.TEXT, i).encodeAsBitmap();
            this.bitmap = encodeAsBitmap;
            imageView.setImageBitmap(encodeAsBitmap);
            this.flmenu.close(true);
        } catch (WriterException unused) {
        }
        this.mydialogue.show();
        imageView2.setOnClickListener(new View.OnClickListener() {
            /* class com.applex.snaplingo.MainActivity.AnonymousClass18 */

            public void onClick(View view) {
                Intent intent = new Intent("android.intent.action.SEND");
                intent.setType("*/*");
                MainActivity.this.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
                Uri parse = Uri.parse(MediaStore.Images.Media.insertImage(MainActivity.this.getContentResolver(), MainActivity.this.bitmap, String.valueOf(System.currentTimeMillis()), (String) null));
                intent.putExtra("android.intent.extra.TEXT", "Generate QRs with SnapLingo! If you haven't downloaded yet, click here: https://play.google.com/store/apps/details?id=com.applex.snaplingo ");
                intent.putExtra("android.intent.extra.STREAM", parse);
                MainActivity.this.startActivity(Intent.createChooser(intent, "Share QR"));
            }
        });
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 == -1) {
                Uri uri = activityResult.getUri();
                resultUri = uri;
                this.pv.setImageURI(uri);
                Bitmap bitmap2 = ((BitmapDrawable) this.pv.getDrawable()).getBitmap();
                TextRecognizer build = new TextRecognizer.Builder(getApplicationContext()).build();
                if (!build.isOperational()) {
                    Toast.makeText(this, "Error", 0).show();
                    return;
                }
                SparseArray<TextBlock> detect = build.detect(new Frame.Builder().setBitmap(bitmap2).build());
                StringBuilder sb = new StringBuilder();
                for (int i3 = 0; i3 < detect.size(); i3++) {
                    sb.append(detect.valueAt(i3).getValue());
                    if (i3 != detect.size() - 1) {
                        sb.append("\n");
                    }
                }
                mResultEt.setText(sb.toString());
                if (mResultEt.length() != 0) {
                    OCRtext = mResultEt.getText().toString();
                    databaseAdder(0);
                }
            }
        } else if (i2 == 204) {
            Toast.makeText(this, "+error", 0).show();
        }
    }

    public void AddData(String str, String str2) {
        if (!this.myDB.addData2(str, str2)) {
            Toast.makeText(this, "Something went wrong :(", 0).show();
        }
    }

    public void databaseAdder(int i) {
        itemID = -1;
        String replaceAll = mResultEt.getText().toString().replaceAll("'", "''");
        if (this.sel == 3) {
            replaceAll = getIntent().getStringExtra(AppMeasurementSdk.ConditionalUserProperty.NAME).replaceAll("'", "''");
        }
        Cursor itemId = this.myDB.getItemId(replaceAll);
        while (itemId.moveToNext()) {
            itemID = itemId.getInt(0);
        }
        if (itemID == -1 && this.sel != 3) {
            AddData(mResultEt.getText().toString(), DateFormat.getDateInstance().format(Calendar.getInstance().getTime()));
            Cursor itemId2 = this.myDB.getItemId(mResultEt.getText().toString().replaceAll("'", "''"));
            while (itemId2.moveToNext()) {
                itemID = itemId2.getInt(0);
            }
        } else if (itemID > -1 && i == 1) {
            String format = DateFormat.getDateInstance().format(Calendar.getInstance().getTime());
            this.myDB.deleteItem(itemID);
            AddData(mResultEt.getText().toString(), format);
            Cursor itemId3 = this.myDB.getItemId(mResultEt.getText().toString().replaceAll("'", "''"));
            while (itemId3.moveToNext()) {
                itemID = itemId3.getInt(0);
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.Save && mResultEt.length() != 0) {
            databaseAdder(1);
            Toast.makeText(this, "Saved", 1).show();
            super.onBackPressed();
        }
        if (itemId == 16908332) {
            if (mResultEt.length() != 0) {
                databaseAdder(0);
            }
            if (isTaskRoot()) {
                startActivity(new Intent(this, MainActivity2.class));
            } else {
                super.onBackPressed();
            }
        } else if (itemId == R.id.share) {
            if (mResultEt.length() > 0) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.TEXT", mResultEt.getText().toString());
                intent.setType("text/plain");
                startActivity(intent);
            } else {
                Toast.makeText(this, "No text found :(", 0).show();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // androidx.activity.ComponentActivity
    public void onBackPressed() {
        if (this.flmenu.isOpened()) {
            this.flmenu.close(true);
            return;
        }
        if (mResultEt.length() != 0) {
            databaseAdder(0);
        }
        if (isTaskRoot()) {
            startActivity(new Intent(this, MainActivity2.class));
        } else {
            super.onBackPressed();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onPause() {
        super.onPause();
        if (mResultEt.length() != 0) {
            OCRtext = mResultEt.getText().toString();
            databaseAdder(0);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity
    public void onResume() {
        super.onResume();
        if (mResultEt.length() != 0) {
            OCRtext = mResultEt.getText().toString();
            databaseAdder(0);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity
    public void onStart() {
        super.onStart();
        if (this.sel == 1 && resultUri == null) {
            Toast.makeText(this, "Image not Detected...", 0).show();
            startActivity(new Intent(this, MainActivity2.class));
        }
    }
}
