package com.example.bulletin_test.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bulletin_test.R;
import com.example.bulletin_test.ui.community.freeInformationActivity;
import com.example.bulletin_test.ui.community.recipeCommunityActivity;
import com.example.bulletin_test.ui.login.MemberInfo;
import com.example.bulletin_test.ui.writingContent.FreePostInfo;
import com.example.bulletin_test.ui.writingContent.RecipePostInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class freeAdapter extends RecyclerView.Adapter<freeAdapter.freeViewHolder> {
    private ArrayList<FreePostInfo> mDataset;
    private Activity activity;
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();//데이터베이스 선언

    static class freeViewHolder extends RecyclerView.ViewHolder{
        public CardView cardView;
        freeViewHolder(Activity activity, CardView v, FreePostInfo freePostInfo){
            super(v);
            cardView = v;
        }
    }

    public freeAdapter(Activity activity, ArrayList<FreePostInfo> freeDataset){
        mDataset = freeDataset;
        this.activity = activity;
    }


    @NotNull
    @Override
    public freeAdapter.freeViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType){
        CardView cardView =(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_free_post, parent,false);
        final freeViewHolder freeViewHolder = new freeViewHolder(activity, cardView, mDataset.get(viewType));
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(activity, freeInformationActivity.class);
                intent.putExtra("freePostInfo", mDataset.get(freeViewHolder.getAdapterPosition()));
                activity.startActivity(intent);
            }
        });
        return freeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull final freeViewHolder holder, int position){

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        CardView cardView = holder.cardView;
        cardView.setLayoutParams(layoutParams);

        TextView title = cardView.findViewById(R.id.freeTitle);
        title.setText(mDataset.get(position).getTitle());

        TextView createdAt = cardView.findViewById(R.id.freeCreatedAt);
        createdAt.setText(new SimpleDateFormat("MM-dd hh:mm", Locale.KOREA).format(mDataset.get(position).getCreatedAt()));

        TextView freePublisher = cardView.findViewById(R.id.freePublisher);
        freePublisher.setText(mDataset.get(position).getUserName());

        TextView recom = cardView.findViewById(R.id.freeRecom);
        recom.setText("추천수 : " + (int) mDataset.get(position).getRecom());

    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }

}
