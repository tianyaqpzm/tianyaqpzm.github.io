# include <stdio.h>
void bubber_sort(int arr[], int n)
{
    int i = 0, j = 0;
    int flag = 1;
    for (i = 0; i < n - 1; i++)
    {
        for (j = i + 1; j < n; j++)
        {
            if (arr[i] > arr[j])
            {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
                flag = 0;
            }
        }
        if (flag == 1)
            break;
    }
}
int main()
{
    int n, k, a;
    int arr[n];
    int i = 0;
    scanf("%d%d",&n,&k);
    for ( i = 0; i < n; i++)
    {
        scanf("%d",&arr[i]);
    }
    bubber_sort(arr, n);
    for (a = 0; a < k; a++)
    {
        printf("%d ",arr[a]);
    }
    return 0;
}