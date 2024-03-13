package nader.islamic.app.dua;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import nader.islamic.app.dua.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    
    private ActivityMainBinding binding;
    private List<Dua> duaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        setSupportActionBar(binding.toolbar);
        
        loadDuaListFromJson();
        
        binding.fab.setOnClickListener(v -> {
            Dua dua = findDuaById(1); // Change the ID here
            if (dua != null) {
                Toast.makeText(MainActivity.this, dua.getDuaArabic(), Toast.LENGTH_SHORT).show();
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
            Gson gson = new Gson();
            duaList = gson.fromJson(inputStreamReader, new TypeToken<List<Dua>>() {}.getType());
            inputStreamReader.close();
        } catch (IOException e) {
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
