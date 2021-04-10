package com.example.whats_app_dark_mode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerViewStories, recyclerViewMessages;
    TextView textViewChats, textViewGroups;
    ImageView iv_home_green, iv_home_white, iv_phone_green, iv_phone_white, iv_camera_green, iv_camera_white, iv_profile_green, iv_profile_white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewStories = findViewById(R.id.rv_stories);
        recyclerViewMessages = findViewById(R.id.rv_messages);
        textViewChats = findViewById(R.id.chats);
        textViewGroups = findViewById(R.id.Groups);
        iv_home_green = findViewById(R.id.home_green);
        iv_home_white = findViewById(R.id.home_white);
        iv_phone_green = findViewById(R.id.phone_green);
        iv_phone_white = findViewById(R.id.phone_white);
        iv_camera_green = findViewById(R.id.camera_green);
        iv_camera_white = findViewById(R.id.camera_white);
        iv_profile_green = findViewById(R.id.profile_green);
        iv_profile_white = findViewById(R.id.profile_white);
        iv_home_green.setOnClickListener(this);
        iv_home_white.setOnClickListener(this);
        iv_phone_green.setOnClickListener(this);
        iv_phone_white.setOnClickListener(this);
        iv_camera_green.setOnClickListener(this);
        iv_camera_white.setOnClickListener(this);
        iv_profile_green.setOnClickListener(this);
        iv_profile_white.setOnClickListener(this);

        textViewChats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewChats.setBackgroundResource(R.drawable.background_cg);
                textViewChats.setTextColor(ContextCompat.getColor(getBaseContext(),R.color.tg_selected));
                textViewGroups.setBackgroundResource(0);
                textViewGroups.setTextColor(ContextCompat.getColor(getBaseContext(),R.color.tg_non_selected));
                Toast.makeText(getBaseContext(),"Chats section",Toast.LENGTH_SHORT).show();
            }
        });

        textViewGroups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewGroups.setBackgroundResource(R.drawable.background_cg);
                textViewGroups.setTextColor(ContextCompat.getColor(getBaseContext(),R.color.tg_selected));
                textViewChats.setBackgroundResource(0);
                textViewChats.setTextColor(ContextCompat.getColor(getBaseContext(),R.color.tg_non_selected));
                Toast.makeText(getBaseContext(),"Groups section",Toast.LENGTH_SHORT).show();
            }
        });

        ArrayList<Storie> stories = getStories();
        StoriesAdapter adapter = new StoriesAdapter(stories,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewStories.setAdapter(adapter);
        recyclerViewStories.setLayoutManager(layoutManager);

        ArrayList<Conversation> conversations = getConversations();
        ConversationAdapter adapter1 = new ConversationAdapter(conversations,getApplicationContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewMessages.setAdapter(adapter1);
        recyclerViewMessages.setLayoutManager(layoutManager1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home_white :
                iv_home_green.setVisibility(View.VISIBLE);
                iv_home_white.setVisibility(View.GONE);
                iv_phone_green.setVisibility(View.GONE);
                iv_phone_white.setVisibility(View.VISIBLE);
                iv_camera_green.setVisibility(View.GONE);
                iv_camera_white.setVisibility(View.VISIBLE);
                iv_profile_green.setVisibility(View.GONE);
                iv_profile_white.setVisibility(View.VISIBLE);
                Toast.makeText(getBaseContext(),"Home",Toast.LENGTH_SHORT).show();
                break;
            case R.id.phone_white :
                iv_home_green.setVisibility(View.GONE);
                iv_home_white.setVisibility(View.VISIBLE);
                iv_phone_green.setVisibility(View.VISIBLE);
                iv_phone_white.setVisibility(View.GONE);
                iv_camera_green.setVisibility(View.GONE);
                iv_camera_white.setVisibility(View.VISIBLE);
                iv_profile_green.setVisibility(View.GONE);
                iv_profile_white.setVisibility(View.VISIBLE);
                Toast.makeText(getBaseContext(),"Phone",Toast.LENGTH_SHORT).show();
                break;
            case R.id.camera_white :
                iv_home_green.setVisibility(View.GONE);
                iv_home_white.setVisibility(View.VISIBLE);
                iv_phone_green.setVisibility(View.GONE);
                iv_phone_white.setVisibility(View.VISIBLE);
                iv_camera_green.setVisibility(View.VISIBLE);
                iv_camera_white.setVisibility(View.GONE);
                iv_profile_green.setVisibility(View.GONE);
                iv_profile_white.setVisibility(View.VISIBLE);
                Toast.makeText(getBaseContext(),"Camera",Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_white :
                iv_home_green.setVisibility(View.GONE);
                iv_home_white.setVisibility(View.VISIBLE);
                iv_phone_green.setVisibility(View.GONE);
                iv_phone_white.setVisibility(View.VISIBLE);
                iv_camera_green.setVisibility(View.GONE);
                iv_camera_white.setVisibility(View.VISIBLE);
                iv_profile_green.setVisibility(View.VISIBLE);
                iv_profile_white.setVisibility(View.GONE);
                Toast.makeText(getBaseContext(),"Profile",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    public ArrayList<Storie> getStories(){
        ArrayList<Storie> stories = new ArrayList<>();

        Storie mine = new Storie("Add","path+");
        stories.add(mine);
        Storie storie = new Storie("FouFa","path");
        stories.add(storie);
        Storie storie1 = new Storie("Dahou","path");
        stories.add(storie1);
        Storie storie2 = new Storie("Reda","path");
        stories.add(storie2);
        Storie storie3 = new Storie("SidAli","path");
        stories.add(storie3);
        Storie storie4 = new Storie("Bahi","path");
        stories.add(storie4);
        Storie storie5 = new Storie("Zouka","path");
        stories.add(storie5);
        Storie storie6 = new Storie("Islam","path");
        stories.add(storie6);
        Storie storie7 = new Storie("Ramzi","path");
        stories.add(storie7);
        Storie storie8 = new Storie("Aimen","path");
        stories.add(storie8);
        Storie storie9 = new Storie("Mehdi","path");
        stories.add(storie9);


        return stories;
    }

    public ArrayList<Conversation> getConversations(){
        ArrayList<Conversation> conversations = new ArrayList<>();

        Conversation c1 = new Conversation("FouFa","Hhhhh that was so nice, Thanks !","9:30",2);
        conversations.add(c1);
        Conversation c2 = new Conversation("Dahou","I don't know","8:23",0);
        conversations.add(c2);
        Conversation c3 = new Conversation("Reda","Good morning","6:00",5);
        conversations.add(c3);
        Conversation c4 = new Conversation("SidAli","Really nice to see you budy","00:12",0);
        conversations.add(c4);
        Conversation c5 = new Conversation("Bahi","Happy BirthDay baby","22:30",0);
        conversations.add(c5);
        Conversation c6 = new Conversation("Islam","Hhhhh that was so nice, Thanks !","16:30",0);
        conversations.add(c6);
        Conversation c7 = new Conversation("Zouka","Hhhhh that was so nice, Thanks !","12:30",0);
        conversations.add(c7);
        Conversation c8 = new Conversation("Aimen","Hhhhh that was so nice, Thanks !","12:30",0);
        conversations.add(c8);
        Conversation c9 = new Conversation("Ramzi","Hhhhh that was so nice, Thanks !","12:30",3);
        conversations.add(c9);
        Conversation c10 = new Conversation("Mehdi","Hhhhh that was so nice, Thanks !","12:30",0);
        conversations.add(c10);

        return conversations;
    }

    public static class StoriesAdapter extends RecyclerView.Adapter<StoriesAdapter.ViewHolder>{

        private ArrayList<Storie> stories;
        private Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView image;
            private ImageView imageOutline;
            private TextView name;

            public ViewHolder(View view) {
                super(view);

                image = (ImageView) view.findViewById(R.id.stor_image);
                imageOutline = (ImageView) view.findViewById(R.id.stor_image_outline);
                name = (TextView) view.findViewById(R.id.stor_name);
            }

        }

        public StoriesAdapter(ArrayList<Storie> stories, Context context) {
            this.stories = stories;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.story, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            float factor = holder.itemView.getContext().getResources().getDisplayMetrics().density;
            if (position == 0){
                holder.image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_add));
                holder.imageOutline.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.background_dashed_image_outline));
                holder.image.getLayoutParams().width = 70;
                holder.image.getLayoutParams().height = 70;
            }else {
                holder.imageOutline.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.background_image_outline));
                holder.image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.img1));
                holder.image.getLayoutParams().width = (int) (48 * factor);
                holder.image.getLayoutParams().height = (int) (48 * factor);
            }
            holder.name.setText(stories.get(position).contactName);
        }

        @Override
        public int getItemCount() {
            return stories.size();
        }
    }

    public static class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ViewHolder>{

        private ArrayList<Conversation> conversations;
        private Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final ImageView image;
            private final TextView name;
            private final TextView message;
            private final TextView time;
            private final TextView unseen;

            public ViewHolder(View view) {
                super(view);

                image = (ImageView) view.findViewById(R.id.conv_image);
                name = (TextView) view.findViewById(R.id.conv_contact_name);
                message = (TextView) view.findViewById(R.id.conv_last_message);
                time = (TextView) view.findViewById(R.id.conv_time);
                unseen = (TextView) view.findViewById(R.id.conv_unseen_messages);
            }
        }

        public ConversationAdapter(ArrayList<Conversation> conversations, Context context) {
            this.conversations = conversations;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.conversation, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.image.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.img1));
            holder.name.setText(conversations.get(position).contactName);
            holder.message.setText(conversations.get(position).lastMessage);
            holder.time.setText(conversations.get(position).time);
            if (conversations.get(position).nbMessagesUnseen != 0){
                holder.unseen.setText(String.valueOf(conversations.get(position).nbMessagesUnseen));
                holder.unseen.setVisibility(View.VISIBLE);
            }else {
                holder.unseen.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public int getItemCount() {
            return conversations.size();
        }
    }

    public class Storie {
        private String contactName;
        private String imagePath;

        public Storie(String contactName, String imagePath) {
            this.contactName = contactName;
            this.imagePath = imagePath;
        }
    }

    public class Conversation {
        private String contactName;
        private String lastMessage;
        private String time;
        private int nbMessagesUnseen;

        public Conversation(String contactName, String lastMessage, String time, int nbMessagesUnseen) {
            this.contactName = contactName;
            this.lastMessage = lastMessage;
            this.time = time;
            this.nbMessagesUnseen = nbMessagesUnseen;
        }
    }
}