# Android assignment

## Method

I have implemented polling method in service worker.
It keeps on continuously polling for incoming tasks and executes them


## Optimization

(*Couldn't implement due to time constraint*)
Polling method in above approach can be optimized.
Rather than polling continuously, we can set a MAX duration for it after
which worker will become IDLE. And whenever a task is added, it becomes
ACTIVE again and polling resumes.