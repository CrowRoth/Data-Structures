public linearsearch(array, arraySize, key) {
    for(int i=0;i<arraySize;i++) {
        if(array[i] == key) {
            return i;
        }
    }
    return -1;
}

public binarysearch(array, arraySize, key) {
    mid = 0;
    low = 0;
    high = arraySize - 1; //to match array index

    while(high >= low) {
        mid = (high + low)/2;
        if(array[mid] < key) {
            low = mid + 1;
        }
        else if(array[mid] > key) {
            high = mid - 1;
        }
        else {
            return mid;
        }
    }
}

//