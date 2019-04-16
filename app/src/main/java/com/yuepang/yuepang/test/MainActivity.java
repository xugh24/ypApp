//package com.yuepang.yuepang.test;
//
//import android.app.Activity;
//
///**
// * Created by xugh on 2019/4/15.
// */
//
//public class MainActivity extends Activity {
//    public class MainActivity extends Activity {
//        private static final int NONE=0;
//        private static final String IMAGE_UNSPECIFIED="image/*";
//        private static final int PHOTOZOOM =2; //触发从图库获取图片的按钮
//        private static final int PHOTOSHOOT=1; //触发拍照获取图片的按钮
//        private static final int PHOTORESULT=3;//结果
//        private ImageView iView;
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//            Button btnShoot=(Button)findViewById(R.id.button1);
//            Button btnAlum=(Button)findViewById(R.id.button2);
//            iView=(ImageView)findViewById(R.id.imageView1);
//            btnAlum.setOnClickListener(new OnClickListener() {
//                //从图库获取照片
//                @Override
//                public void onClick(View v) {
//                    Intent intent=new Intent(Intent.ACTION_PICK);//选择数据的意图
//                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,IMAGE_UNSPECIFIED );//设置数据和类型，从图库中获取图片
//                    startActivityForResult(intent, PHOTOZOOM);//执行意图并传入请求码（请求码根据业务需要自行指定）
//                }
//            });
//            btnShoot.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//拍照到指定目录的意图
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"temp.jpg")));//设置照片保存地址
//                    startActivityForResult(intent, PHOTOSHOOT);//执行意图并传入请求码
//                }
//            });
//        }
//        /**
//         * 接收之前activity结束后返回的数据，并处理
//         */
//        protected void onActivityResult(int requestCode,int resultCode,Intent data){
//            Log.v("test", "onActivityResult is: requestCode: "+requestCode+" resultCode: "+resultCode+" data: "+data);
//            //拍照和图库获取都需要有activity返回，拍照不需要data返回
//            if(resultCode==NONE){
//                return;
//            }
//            if(requestCode==PHOTOSHOOT){
//                File picture = new File(Environment.getExternalStorageDirectory()+"/temp.jpg");//获取之前拍照保存的照片地址
//                startPhotoZoom(Uri.fromFile(picture));//获取地址并调用裁剪
//            }
//            //若无数据，则没有从相册得到照片，直接返回
//            if(data==null)
//                return;
//            if(requestCode==PHOTOZOOM){
//                startPhotoZoom(data.getData());//返回的是地址，然后对图片裁剪
//            }
//            //如果得到的是结果图片，就显示出来，处理结果
//            if(requestCode==PHOTORESULT){
//                Bundle bundle=data.getExtras();//把数据取出来，Bundle是一个装数据的可以在activity之间传输的类
//                Bitmap bitmap =bundle.getParcelable("data");//获取位图
//                ByteArrayOutputStream stream=new ByteArrayOutputStream();//获取字节数组输出流
//                bitmap.compress(Bitmap.CompressFormat.JPEG,75,stream);//把图片在流中按照指定格式压缩
//                iView.setImageBitmap(bitmap);//设置位图，显示
//            }
//            super.onActivityResult(requestCode, resultCode, data);//使得参数传递到之前的activity
//        }
//        /**
//         * 照片压缩
//         * @param uri
//         */
//        public void startPhotoZoom(Uri uri){
//            Intent intent =new Intent("com.android.camera.action.CROP");//裁剪意图
//            intent.setDataAndType(uri, IMAGE_UNSPECIFIED);//设置裁剪的地址和类型
//            intent.putExtra("crop", false);//把未裁剪信息附加到intent上
//            //设置宽高比例为1:1
//            intent.putExtra("aspectX", 1);
//            intent.putExtra("aspectY", 1);
//            //设置裁剪图片的宽高
//            intent.putExtra("return-data", true);//要返回值
//            startActivityForResult(intent, PHOTORESULT);//执行意图，赋予请求码
//        }
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.main, menu);
//            return true;
//        }
//    }
//}
