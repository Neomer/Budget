package my.neomer.budget.activities.main;

import android.content.Context;
import android.content.pm.LauncherApps;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import my.neomer.budget.R;
import my.neomer.budget.core.types.TransactionCategory;
import my.neomer.budget.models.Transaction;
import my.neomer.budget.widgets.MoneyView;

public class TransactionsRecyclerViewAdapter extends RecyclerView.Adapter<TransactionsRecyclerViewAdapter.TransactionViewHolder> {

    private List<Transaction> transactionList;
    private OnItemClickListener onItemClickListener;

    public TransactionsRecyclerViewAdapter(List<Transaction> transactionList, OnItemClickListener onItemClickListener) throws NullPointerException {
        if (transactionList == null)
        {
            throw new NullPointerException("Transaction list must not be null!");
        }
        this.transactionList = transactionList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_recyclerviewitem_layout, viewGroup, false);
        TransactionViewHolder pvh = new TransactionViewHolder(v, onItemClickListener);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder transactionViewHolder, int i) {
        transactionViewHolder.txtTransactionName.setText(transactionList.get(i).getDetailed());
        transactionViewHolder.txtDate.setText(transactionList.get(i).getDate().toString("yyyy-MM-dd HH:mm"));
        transactionViewHolder.txtDetailedText.setText("");
        transactionViewHolder.txtAmount.setMoney(transactionList.get(i).getAmount());
        TransactionCategory category = transactionList.get(i).getCategory();
        transactionViewHolder.txtCategory.setText(
                transactionViewHolder.Context.getResources().getString(
                        category != null ? category.getName() : R.string.empty_category));
        transactionViewHolder.imageViewCategory.setImageDrawable(
                transactionViewHolder.Context.getResources().getDrawable(
                        category != null ? category.getImage() : R.drawable.ic_005_faq));
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
        notifyDataSetChanged();
    }

    class TransactionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtTransactionName;
        TextView txtDetailedText;
        MoneyView txtAmount;
        TextView txtCategory;
        TextView txtDate;
        ImageView imageViewCategory;
        Context Context;
        OnItemClickListener onItemClickListener;

        TransactionViewHolder(@NonNull View itemView, @NonNull OnItemClickListener onItemClickListener) {
            super(itemView);

            txtTransactionName = itemView.findViewById(R.id.txtTransactionName);
            txtDetailedText = itemView.findViewById(R.id.txtFullText);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtDate = itemView.findViewById(R.id.txtDateTime);
            imageViewCategory = itemView.findViewById(R.id.imageViewCategory);
            Context = itemView.getContext();

            this.onItemClickListener = onItemClickListener;
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
