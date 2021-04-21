package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;

public class KalendarzFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kalendarz, container, false);
    }

    //odwołać się do pliku, który obrazuje cykle życia w androidzie, dlatego używamy metody onViewCreated, bo jest poniżej onCreateView()
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //wywołanie funkcji
        initCalender();


    }

    private void initCalender() {
 //nie mozemy tak po prostu wywołać metody findViewById w fragments, więc wywołujemy getView() (które wywołuje root view, czyli view wywołąny przez onCreateView), a z tego można wywołać findViewById
  //nie można też użyc getView() po onCreateView(). Nie można tego użyć w onCreate() albo onCreateView()!
        CalendarView calendarView= (CalendarView) getView().findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {

                String date = year+"/"+month+"/"+dayOfMonth;
                //w fragments nie pobiera się nazwa_fragments.this, tylko najlepiej getActivity()
                Intent intent = new Intent (getActivity(), ExtraActivity.class);

                intent.putExtra("date", date);

                startActivity(intent);
            }
        });
    }
}
