package my.neomer.budget.activities.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import my.neomer.budget.R;
import my.neomer.budget.core.types.TransactionCategory;
import my.neomer.budget.models.Transaction;
import my.neomer.budget.widgets.MoneyView;

public class TransactionsRecyclerViewAdapter extends RecyclerView.Adapter<TransactionsRecyclerViewAdapter.TransactionViewHolder> {

    private List<Transaction> transactionList;

    public TransactionsRecyclerViewAdapter(List<Transaction> transactionList) throws NullPointerException {
        if (transactionList == null)
        {
            throw new NullPointerException("Transaction list must not be null!");
        }
        this.transactionList = transactionList;
    }

    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.transaction_recyclerviewitem_layout, viewGroup, false);
        TransactionViewHolder pvh = new TransactionViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder transactionViewHolder, int i) {
        String title = transactionList.get(i).getTitle();
        /*
        transactionViewHolder.txtTransactionName.setText(
                title != null && !title.isEmpty() ? title :
                        (transactionList.get(i).getType() == Transaction.TransactionType.Income ?
                                transactionViewHolder.Context.getString(R.string.money_income) :
                                transactionViewHolder.Context.getString(R.string.money_spend)));
        transactionViewHolder.txtDetailedText.setText(transactionList.get(i).getDetailed());
        */
        transactionViewHolder.txtTransactionName.setText(transactionList.get(i).getDetailed());
        transactionViewHolder.txtDate.setText(transactionList.get(i).getDate().toString("yyyy-MM-dd HH:mm"));
        transactionViewHolder.txtDetailedText.setText("");
        transactionViewHolder.txtAmount.setMoney(transactionList.get(i).getAmount());
        TransactionCategory category = transactionList.get(i).getCategory();
        transactionViewHolder.txtCategory.setText(
                transactionViewHolder.Context.getResources().getString(
                        category != null ? category.getName() : R.string.empty_category));
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

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTransactionName;
        public TextView txtDetailedText;
        public MoneyView txtAmount;
        public TextView txtCategory;
        public TextView txtDate;
        public Context Context;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTransactionName = itemView.findViewById(R.id.txtTransactionName);
            txtDetailedText = itemView.findViewById(R.id.txtFullText);
            txtAmount = itemView.findViewById(R.id.txtAmount);
            txtCategory = itemView.findViewById(R.id.txtCategory);
            txtDate = itemView.findViewById(R.id.txtDateTime);
            Context = itemView.getContext();
        }
    }
}
