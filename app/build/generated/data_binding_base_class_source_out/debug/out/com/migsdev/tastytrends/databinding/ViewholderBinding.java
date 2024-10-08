// Generated by view binder compiler. Do not edit!
package com.migsdev.tastytrends.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.migsdev.tastytrends.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ViewholderBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView stallsLogo;

  @NonNull
  public final TextView stallsName;

  private ViewholderBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView stallsLogo,
      @NonNull TextView stallsName) {
    this.rootView = rootView;
    this.stallsLogo = stallsLogo;
    this.stallsName = stallsName;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewholderBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewholderBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.viewholder, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewholderBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.stallsLogo;
      ImageView stallsLogo = ViewBindings.findChildViewById(rootView, id);
      if (stallsLogo == null) {
        break missingId;
      }

      id = R.id.stallsName;
      TextView stallsName = ViewBindings.findChildViewById(rootView, id);
      if (stallsName == null) {
        break missingId;
      }

      return new ViewholderBinding((ConstraintLayout) rootView, stallsLogo, stallsName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
