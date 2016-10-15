# #**likeProphesy**

likeProphesy app is made to  predict the possible number of likes and the
people who will like a post by performing the analysis of likes on previous
posts on facebook.

#**About of likeProphesy**
This app basically implements three algorithms which helps in predicting the number of likes and those who will like it on the basis of the prediction algorithms.

Here's the link to the algorithms: [Prediction Analysis](src/Final.pdf)

#**Setup for Developers**

1.Make sure you have downloaded a Java IDE.
2.Go to the project repo and fork it by clicking "Fork"
3.If you are working on Windows, download Git Bash for Windows to get a full Unix bash with Git functionality
4.Clone the repo to your desktop git clone git@github.com:your_name/likeProphesy.git
5.Open the project with Android Studio

#**Configure remotes**

When a repository is cloned, it has a default remote called origin that points to your fork on GitHub, not the original repository it was forked from. To keep track of the original repository, you should add another remote named upstream:

1.Open terminal or git bash in your local repository and type:

    git remote add upstream https://github.com/likeProphesy.git

    Run git remote -v to check the status, you should see something like the following:

        origin https://github.com/YOUR_USERNAME/likeProphesy.git (fetch)

        origin https://github.com/YOUR_USERNAME/likeProphesy.git (push)

        upstream https://github.com/likeProphesy.git (fetch)

        upstream https://github.com/likeProphesy.git (push)

2.To update your local copy with remote changes, run the following:

    git fetch upstream

    git merge upstream/master

    This will give you an exact copy of the current remote, make sure you don't have any local changes.

#**Contributing and developing a feature**

    Make sure you are in the master branch git checkout master
    Sync your copy git pull
    Create a new branch with a meaningful name git checkout -b branch_name
    Develop your feature on Android Studio and run it using the emulator or connecting your own Android device
    Clean your project from Android Studio Build/Clean project
    Add the files you changed git add file_name (avoid using git add .)
    Commit your changes git commit -m "Message briefly explaining the feature"
    Keep one commit per feature. If you forgot to add changes, you can edit the previous commit git commit --amend
    Push to your repo git push origin branch-name
    Go into the Github repo and create a pull request explaining your changes
    If you are requested to make changes, edit your commit using git commit --amend, push again and the pull request will edit automatically
    You will need to add a message on the pull request notifying your changes to your reviewer


