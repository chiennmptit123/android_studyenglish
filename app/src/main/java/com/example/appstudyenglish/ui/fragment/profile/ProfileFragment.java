package com.example.appstudyenglish.ui.fragment.profile;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.appstudyenglish.R;
import com.example.appstudyenglish.databinding.FragmentProfileBinding;
import com.example.appstudyenglish.model.User;
import com.example.appstudyenglish.ui.cus_tom_dialog.CustomProgressDialog;
import com.example.appstudyenglish.ui.log_in.dang_nhap.LogInActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private CustomProgressDialog dialog;
    public static final int MY_REQUEST_CODE = 10;
    private Uri mUri;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private User user;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile,container,false);
        dialog = new CustomProgressDialog(getContext());
        setUserInformation();
        onClickSetAvatar();
        onClickLogout();
        return binding.getRoot();
    }

    private void onClickLogout() {
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getContext(), LogInActivity.class));
            }
        });
    }

    private void onClickSetAvatar() {
        binding.imgAvatarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickRequestPermission();
            }
        });
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onUpdateInfor();
            }
        });

    }

    private void onUpdateInfor() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser==null){
            return;
        }
        dialog.show();
        UserProfileChangeRequest profileChangeRequest = new UserProfileChangeRequest.Builder()
                .setPhotoUri(mUri)
                .build();
        firebaseUser.updateProfile(profileChangeRequest).addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                dialog.dismiss();
                Glide.with(getActivity()).load(firebaseUser.getPhotoUrl()).error(R.drawable.avata).into(binding.imgAvatarUser);
                binding.btnSave.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Cập nhập ảnh đại diện thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void onClickRequestPermission() {
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
                openGallery();
            }else {
                String [] permison = {Manifest.permission.READ_EXTERNAL_STORAGE};
                getActivity().requestPermissions(permison,MY_REQUEST_CODE);
            }
        }
    }
    final private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , result -> {
                if (result.getResultCode() == RESULT_OK){
                    Intent intent = result.getData();
                    if (intent==null){
                        return;
                    }
                    Uri uri = intent.getData();
                    setmUri(uri);
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),uri);
                        setBitmapImageView(bitmap);
                        binding.btnSave.setVisibility(View.VISIBLE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }

    private void  setUserInformation() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser==null){
            return;
        }
        else {
            Uri photoUrl = firebaseUser.getPhotoUrl();
            String userID = firebaseUser.getUid();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference(userID);
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    user = snapshot.getValue(User.class);
                    binding.txtEmailUser.setText(user.getEmail());
                    binding.txtNameUser.setText(user.getName());
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            Glide.with(getActivity()).load(photoUrl).error(R.drawable.avata).into(binding.imgAvatarUser);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode ==MY_REQUEST_CODE){
            if (grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }
    public void setBitmapImageView(Bitmap bitmapImageView){
        Glide.with(binding.imgAvatarUser).load(bitmapImageView).into(binding.imgAvatarUser);
    }
}
