package com.neotrick.callinfos.home_section.help_section;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.neotrick.callinfos.R;
import com.neotrick.callinfos.home_section.help_section.history_section.model.history_response.Data;

import java.util.ArrayList;

public class AdapterHistoryChat extends RecyclerView.Adapter<AdapterHistoryChat.MyViewHolder> {
    ArrayList<Data> mOnlineShop;


    public AdapterHistoryChat(ArrayList<Data> mOnlineShop, FragmentActivity activity) {
        this.mOnlineShop = mOnlineShop;
    }

    @NonNull
    @Override
    public  AdapterHistoryChat.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_chat, parent, false);

        return new AdapterHistoryChat.MyViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHistoryChat.MyViewHolder holder, int position) {
        holder.tv_user_time.setText (mOnlineShop.get (position).getMessageTimestamp ());
        holder.incoming.setText (mOnlineShop.get (position).getMessage ());
        holder.tv_reply_time.setText (mOnlineShop.get (position).getReplyTimestamp ());
        holder.outgoing.setText (mOnlineShop.get (position).getReply ());
    }


    @Override
    public int getItemCount() {
        return mOnlineShop.size ();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_user_time;
        public TextView tv_reply_time;
        public TextView incoming;
        public TextView outgoing;
        public MyViewHolder(@NonNull View itemView) {
            super (itemView);

            outgoing=itemView.findViewById(R.id.outgoing);
            incoming = itemView.findViewById(R.id.incoming);
            tv_user_time = itemView.findViewById(R.id.tv_user_time);
            tv_reply_time = itemView.findViewById(R.id.tv_reply_time);


        }
    }
}
