package nader.islamic.app.dua;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import nader.islamic.app.dua.databinding.DuaItemBinding;

public class DuaAdapter extends RecyclerView.Adapter<DuaAdapter.DuaViewHolder> {

    private List<Dua> duaList;

    public DuaAdapter(List<Dua> duaList) {
        this.duaList = duaList;
    }

    @NonNull
    @Override
    public DuaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        DuaItemBinding itemBinding = DuaItemBinding.inflate(layoutInflater, parent, false);
        return new DuaViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull DuaViewHolder holder, int position) {
        Dua dua = duaList.get(position);
        holder.bind(dua);
    }

    @Override
    public int getItemCount() {
        return duaList.size();
    }

    public static class DuaViewHolder extends RecyclerView.ViewHolder {

        private final DuaItemBinding binding;

        public DuaViewHolder(@NonNull DuaItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Dua dua) {
            binding.duaArabicTextView.setText(dua.getDuaa_arabic());
            binding.duaMeaningTextView.setText(dua.getDuaa_meaning());
        }
    }
}
