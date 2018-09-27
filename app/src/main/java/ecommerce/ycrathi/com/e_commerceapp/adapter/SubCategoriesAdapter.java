package ecommerce.ycrathi.com.e_commerceapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.databinding.CatRowItemBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Categories;

public class SubCategoriesAdapter extends RecyclerView.Adapter<SubCategoriesAdapter.MyViewHolder> {

    private ArrayList<Categories> categories;
    private LayoutInflater layoutInflater;
    private CategoriesAdapterListener listener;

    public SubCategoriesAdapter(ArrayList<Categories> categories, CategoriesAdapterListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        CatRowItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.cat_row_item, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.binding.setCatRow(categories.get(position));
        holder.binding.categoryItemLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onSubCatClicked(categories.get(position), position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public interface CategoriesAdapterListener {
        void onSubCatClicked(Categories post, int clickedPosition);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CatRowItemBinding binding;

        public MyViewHolder(final CatRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}