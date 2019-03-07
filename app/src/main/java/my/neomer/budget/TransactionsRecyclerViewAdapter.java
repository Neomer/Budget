package my.neomer.budget;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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
        transactionViewHolder.txtTransactionName.setText(transactionList.get(i).getTitle());
        transactionViewHolder.txtDetailedText.setText(transactionList.get(i).getDetailed());
        transactionViewHolder.txtAmount.setText(transactionList.get(i).getAmount());
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public class TransactionViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTransactionName;
        public TextView txtDetailedText;
        public MoneyView txtAmount;

        public TransactionViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTransactionName = itemView.findViewById(R.id.txtTransactionName);
            txtDetailedText = itemView.findViewById(R.id.txtFullText);
            txtAmount = itemView.findViewById(R.id.txtAmount);
        }
    }
}
