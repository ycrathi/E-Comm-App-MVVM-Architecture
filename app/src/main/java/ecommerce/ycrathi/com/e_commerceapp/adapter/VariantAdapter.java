package ecommerce.ycrathi.com.e_commerceapp.adapter;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.databinding.VariantRowItemBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Variants;

public class VariantAdapter extends RecyclerView.Adapter<VariantAdapter.MyViewHolder> {

    private ArrayList<Variants> variantsArrayList;
    private LayoutInflater layoutInflater;
    private VariantAdapterListener listener;

    public VariantAdapter(ArrayList<Variants> variantsArrayList, VariantAdapterListener listener) {
        this.variantsArrayList = variantsArrayList;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        VariantRowItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.variant_row_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setVariant(variantsArrayList.get(position));
        holder.binding.variantColorIV.setBackgroundColor(Color.parseColor(getCurrentVariantColor(variantsArrayList.get(position).getColor())));

        holder.binding.variantRowLL.setOnClickListener(v -> {
            if (listener != null) {
                listener.onVariantClicked(variantsArrayList.get(position));
            }
        });

        if (position == 0) {
            holder.binding.variantRowLL.performClick();
        }

    }

    public String getCurrentVariantColor(String color) {
        switch (color) {
            case "Blue":
                return "#0000FF";
            case "Red":
                return "#FF0000";
            case "White":
                return "#FFFFFF";
            case "Black":
                return "#000000";
            case "Golden":
                return "#FFDF00";
            case "Silver":
                return "#C0C0C0";
            case "Brown":
                return "#A52A2A";
            case "Green":
                return "#008000";
            case "Yellow":
                return "#FFFF00";
            case "Light Blue":
                return "#0000FF";
            case "Grey":
                return "#808080";
            default:
                return "#FFFFF";
        }
    }

    @Override
    public int getItemCount() {
        return variantsArrayList.size();
    }

    public interface VariantAdapterListener {
        void onVariantClicked(Variants variants);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final VariantRowItemBinding binding;

        public MyViewHolder(final VariantRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}