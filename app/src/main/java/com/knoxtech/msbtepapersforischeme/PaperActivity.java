package com.knoxtech.msbtepapersforischeme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class PaperActivity extends AppCompatActivity{
    public NoteAdapter adapter;
    public String branch;
    public String paperType;
    public String semester;
    public String year;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_paper);

        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.paperRecycler);

        Bundle bundle2 = this.getIntent().getExtras();
        if (bundle2 != null) {
            this.paperType = bundle2.getString("paperType");
            this.branch = bundle2.getString("Branch");
            this.semester = bundle2.getString("Semester");
            this.year = bundle2.getString("Year");
        }
        CollectionReference collectionReference = firebaseFirestore.collection(paperType).document(branch).collection(semester).document(year).collection("Data");
        Query query = collectionReference.orderBy("title", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();
        adapter=new NoteAdapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Note note = documentSnapshot.toObject(Note.class);
                String url=note.getUrl();
//                Intent intent = new Intent(PaperActivity.this, PreviewActivity.class);
//                Bundle bundle = new Bundle();
//                assert note != null;
//                bundle.putString("ttl", note.getTitle());
//                bundle.putString("url", note.getUrl());
//                intent.putExtras(bundle);
//                startActivity(intent);
                //Uri path = Uri.fromFile(file);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse(url), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}