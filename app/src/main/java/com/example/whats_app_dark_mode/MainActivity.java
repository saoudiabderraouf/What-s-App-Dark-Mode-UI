package com.example.whats_app_dark_mode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerViewStories, recyclerViewStories2, recyclerViewStories1, recyclerViewMessages;
    TextView textViewChats, textViewGroups;
    ImageView iv_home_green, iv_home_white, iv_phone_green, iv_phone_white, iv_camera_green, iv_camera_white, iv_profile_green, iv_profile_white;
    ImageView changeView;
    LinearLayout convos, cvContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        convos = findViewById(R.id.ll_convos);
        cvContainer = findViewById(R.id.ll_change_view_container);
        changeView = findViewById(R.id.change_view);
        recyclerViewStories = findViewById(R.id.rv_stories);
        recyclerViewStories1 = findViewById(R.id.rv_stories1);
        recyclerViewStories2 = findViewById(R.id.rv_stories2);
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

        ArrayList<Storie> stories = getStories();
        StoriesAdapter adapter = new StoriesAdapter(stories,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewStories.setAdapter(adapter);
        recyclerViewStories.setLayoutManager(layoutManager);

        ArrayList<Storie2> stories2 = getStories2();
        StoriesAdapter2 adapter2 = new StoriesAdapter2(stories2,getApplicationContext());
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewStories1.setAdapter(adapter2);
        recyclerViewStories1.setLayoutManager(layoutManager2);
        ArrayList<Storie2> stories3 = getStories22();
        StoriesAdapter2 adapter3 = new StoriesAdapter2(stories3,getApplicationContext());
        RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewStories2.setAdapter(adapter3);
        recyclerViewStories2.setLayoutManager(layoutManager3);

        ArrayList<Conversation> conversations = getConversations();
        ConversationAdapter adapter1 = new ConversationAdapter(conversations,getApplicationContext());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recyclerViewMessages.setAdapter(adapter1);
        recyclerViewMessages.setLayoutManager(layoutManager1);

        changeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams) convos.getLayoutParams();
                RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) cvContainer.getLayoutParams();

                if (recyclerViewStories.getVisibility() == View.VISIBLE){
                    recyclerViewStories.setVisibility(View.GONE);
                    recyclerViewStories1.setVisibility(View.VISIBLE);
                    recyclerViewStories2.setVisibility(View.VISIBLE);
                    params1.addRule(RelativeLayout.BELOW,recyclerViewStories2.getId());
                    params2.addRule(RelativeLayout.BELOW,recyclerViewStories2.getId());
                    convos.setLayoutParams(params1);
                    cvContainer.setLayoutParams(params2);
                }else {
                    recyclerViewStories.setVisibility(View.VISIBLE);
                    recyclerViewStories1.setVisibility(View.GONE);
                    recyclerViewStories2.setVisibility(View.GONE);
                    params1.addRule(RelativeLayout.BELOW,recyclerViewStories.getId());
                    params2.addRule(RelativeLayout.BELOW,recyclerViewStories.getId());
                    convos.setLayoutParams(params1);
                    cvContainer.setLayoutParams(params2);
                }
            }
        });

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

    public ArrayList<Storie2> getStories2(){
        ArrayList<Storie2> stories = new ArrayList<>();

        Storie2 mine = new Storie2("Add","path+",0);
        stories.add(mine);
        Storie2 storie1 = new Storie2("FouFa","path",2);
        stories.add(storie1);
        Storie2 storie2 = new Storie2("FouFa","path",1);
        stories.add(storie2);
        Storie2 storie3 = new Storie2("FouFa","path",0);
        stories.add(storie3);
        Storie2 storie4 = new Storie2("FouFa","path",5);
        stories.add(storie4);
        Storie2 storie5 = new Storie2("FouFa","path",1);
        stories.add(storie5);
        Storie2 storie6 = new Storie2("FouFa","path",1);
        stories.add(storie6);
        Storie2 storie7 = new Storie2("FouFa","path",0);
        stories.add(storie7);
        Storie2 storie8 = new Storie2("FouFa","path",0);
        stories.add(storie8);
        Storie2 storie9 = new Storie2("FouFa","path",0);
        stories.add(storie9);
        Storie2 storie10 = new Storie2("FouFa","path",6);
        stories.add(storie10);

        return stories;
    }

    public ArrayList<Storie2> getStories22(){
        ArrayList<Storie2> stories = new ArrayList<>();

        Storie2 storie1 = new Storie2("FouFa","path",2);
        stories.add(storie1);
        Storie2 storie2 = new Storie2("FouFa","path",1);
        stories.add(storie2);
        Storie2 storie3 = new Storie2("FouFa","path",0);
        stories.add(storie3);
        Storie2 storie4 = new Storie2("FouFa","path",5);
        stories.add(storie4);
        Storie2 storie5 = new Storie2("FouFa","path",1);
        stories.add(storie5);
        Storie2 storie6 = new Storie2("FouFa","path",1);
        stories.add(storie6);
        Storie2 storie7 = new Storie2("FouFa","path",0);
        stories.add(storie7);
        Storie2 storie8 = new Storie2("FouFa","path",0);
        stories.add(storie8);
        Storie2 storie9 = new Storie2("FouFa","path",0);
        stories.add(storie9);
        Storie2 storie10 = new Storie2("FouFa","path",6);
        stories.add(storie10);

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

    public static class StoriesAdapter2 extends RecyclerView.Adapter<StoriesAdapter2.ViewHolder>{

        private ArrayList<Storie2> stories;
        private Context context;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView image;
            private ImageView imageOutline;
            private TextView name;
            private LinearLayout nbStories;
            private RelativeLayout otherStories;
            private LinearLayout myStory;

            public ViewHolder(View view) {
                super(view);

                image = (ImageView) view.findViewById(R.id.stor_image);
                imageOutline = (ImageView) view.findViewById(R.id.stor_image_outline);
                name = (TextView) view.findViewById(R.id.stor_name);
                nbStories = (LinearLayout) view.findViewById(R.id.ll_nb_stories);
                otherStories = (RelativeLayout) view.findViewById(R.id.other_stories);
                myStory = (LinearLayout) view.findViewById(R.id.my_story);
            }

        }

        public StoriesAdapter2(ArrayList<Storie2> stories, Context context) {
            this.stories = stories;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.story_v2, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            float factor = holder.itemView.getContext().getResources().getDisplayMetrics().density;
            holder.name.setText(stories.get(position).contactName);
            if (stories.get(position).imagePath == "path+"){
                holder.otherStories.setVisibility(View.GONE);
                holder.myStory.setVisibility(View.VISIBLE);
            }else {
                holder.otherStories.setVisibility(View.VISIBLE);
                holder.myStory.setVisibility(View.GONE);

                if (stories.get(position).nbStories > 0) {
                    holder.nbStories.removeAllViews();
                    holder.nbStories.setWeightSum(stories.get(position).nbStories);
                    for (int i = 0; i < stories.get(position).nbStories; i++) {
                        LinearLayout linearLayout = new LinearLayout(context);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (0 * factor), (int) (2 * factor), 1f);
                        params.setMargins((int) (2 * factor), 0, 0, 0);
                        linearLayout.setLayoutParams(params);
                        if (i == 0) {
                            linearLayout.setBackground(ContextCompat.getDrawable(context, R.color.white));
                        } else {
                            linearLayout.setBackground(ContextCompat.getDrawable(context, R.color.storie_non_vue));
                        }
                        holder.nbStories.addView(linearLayout);
                    }
                }else {
                    holder.nbStories.removeAllViews();
                }
            }
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

    public class Storie2 {
        private String contactName;
        private String imagePath;
        private int nbStories;

        public Storie2(String contactName, String imagePath, int nbStories) {
            this.contactName = contactName;
            this.imagePath = imagePath;
            this.nbStories = nbStories;
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