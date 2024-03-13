package nader.islamic.app.dua;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import nader.islamic.app.dua.databinding.ActivityMainBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    
    private ActivityMainBinding binding;
    private List<Dua> duaList;
    private RecyclerView recyclerView;
    private DuaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);
        
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        duaList = new ArrayList<>();
        adapter = new DuaAdapter(duaList);
        recyclerView.setAdapter(adapter);
        
        loadDuaListFromJson();
        
        binding.fab.setOnClickListener(v -> {
            Dua dua = findDuaById(1); // Change the ID here
            if (dua != null) {
                Toast.makeText(MainActivity.this, dua.getDuaa_arabic(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Dua not found", Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void loadDuaListFromJson() {
    AssetManager assetManager = getAssets();
    try {
        InputStream inputStream = assetManager.open("dua.json");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        int data = inputStreamReader.read();
        while (data != -1) {
            stringBuilder.append((char) data);
            data = inputStreamReader.read();
        }
        String jsonContent = stringBuilder.toString();
        inputStreamReader.close();
        parseJson(jsonContent);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private void parseJson(String jsonContent) {
    try {
        JSONArray jsonArray = new JSONArray(jsonContent);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int id = jsonObject.getInt("id");
            String duaaArabic = jsonObject.getString("duaa_arabic");
            String duaaMeaning = jsonObject.getString("duaa_meaning");
            JSONArray tagsArray = jsonObject.getJSONArray("tags");
            List<String> tags = new ArrayList<>();
            for (int j = 0; j < tagsArray.length(); j++) {
                tags.add(tagsArray.getString(j));
            }
            Dua dua = new Dua(id, duaaArabic, duaaMeaning, tags);
            duaList.add(dua);
        }
        adapter.notifyDataSetChanged(); // Notify adapter after data is loaded
    } catch (JSONException e) {
        e.printStackTrace();
    }
}

    
    private Dua findDuaById(int id) {
        for (Dua dua : duaList) {
            if (dua.getId() == id) {
                return dua;
            }
        }
        return null;
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
