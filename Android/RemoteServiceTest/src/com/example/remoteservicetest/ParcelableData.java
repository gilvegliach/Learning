package com.example.remoteservicetest;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableData implements Parcelable
{
	private int mInt;
	private String mString;

	public ParcelableData(int aInt, String aString)
	{
		mInt = aInt;
		mString = aString;
	}

	public ParcelableData(ParcelableData data)
	{
		mInt = data.mInt;
		mString = data.mString;
	}

	public void process()
	{
		mInt *= -1;
		StringBuilder sb = new StringBuilder(mString);
		mString = sb.reverse().toString();
	}

	@Override
	public String toString()
	{
		return "ParcelableData{ mInt=" + mInt + ", mString=" + mString + " }";
	}

	@Override
	public boolean equals(Object o)
	{
		if (!(o instanceof ParcelableData))
			return false;

		ParcelableData data = (ParcelableData) o;
		return data.mInt == mInt && data.mString.equals(mString);
	}

	@Override
	public int describeContents()
	{
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeInt(mInt);
		dest.writeString(mString);
	}

	public static final Parcelable.Creator<ParcelableData> CREATOR = new Parcelable.Creator<ParcelableData>()
	{
		@Override
		public ParcelableData createFromParcel(Parcel source)
		{
			int aInt = source.readInt();
			String aString = source.readString();
			return new ParcelableData(aInt, aString);
		}

		@Override
		public ParcelableData[] newArray(int size)
		{
			return new ParcelableData[size];
		}
	};
}
