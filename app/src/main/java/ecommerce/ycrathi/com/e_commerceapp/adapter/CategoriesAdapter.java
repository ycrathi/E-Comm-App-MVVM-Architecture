package ecommerce.ycrathi.com.e_commerceapp.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;

import ecommerce.ycrathi.com.e_commerceapp.R;
import ecommerce.ycrathi.com.e_commerceapp.databinding.CatRowItemBinding;
import ecommerce.ycrathi.com.e_commerceapp.model.Categories;
import ecommerce.ycrathi.com.e_commerceapp.model.ECommPojo;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {

    private ECommPojo pojo;
    private LayoutInflater layoutInflater;
    private CategoriesAdapterListener listener;

    public CategoriesAdapter(ECommPojo pojo, CategoriesAdapterListener listener) {
        this.pojo = pojo;
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
        holder.binding.setCatRow(pojo.getCategories().get(position));
        holder.binding.categoryItemLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (pojo.getCategories().get(position).getChild_categories().size() > 0) {
                        //subcat available


                        HashMap<Integer, Categories> hashMap = new HashMap();
                        for (Categories cat : pojo.getCategories()) {
                            hashMap.put(cat.getId(), cat);
                        }


                        ArrayList<Categories> subCatList = new ArrayList<>();
                        for (int i = 0; i < pojo.getCategories().get(position).getChild_categories().size(); i++) {
                            int catId = pojo.getCategories().get(position).getChild_categories().get(i);


                            Categories cat = hashMap.get(catId);
                            subCatList.add(cat);
                            Log.d("Yogesh", " Subcat " + cat.getName());
                        }

                        listener.showSubCat(subCatList);

                    } else {
                        //subcat not available
                        listener.onCatClicked(pojo.getCategories().get(position), position);
                    }
                }
            }
        });
    }

    public void performClickAction(View view){

    }

    @Override
    public int getItemCount() {
        return pojo.getCategories().size();
    }

    public interface CategoriesAdapterListener {
        void onCatClicked(Categories post, int clickedPosition);

        void showSubCat(ArrayList<Categories> subCatList);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final CatRowItemBinding binding;

        public MyViewHolder(final CatRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}