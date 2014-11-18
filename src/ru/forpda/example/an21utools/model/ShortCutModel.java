package ru.forpda.example.an21utools.model;

import ru.forpda.example.an21utools.R;
import ru.forpda.example.an21utools.util.ICommand;

/**
 * Created by max on 18.11.2014.
 */
public class ShortCutModel {

    public class ShortCutItem{
        String Name;
        String Action;
        int ResourceId;
        ICommand Command;

        public ShortCutItem(String name, String action, int resourceId,ICommand  command) {
            Name = name;
            Action = action;
            ResourceId = resourceId;
            this.Command  = command;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getAction() {
            return Action;
        }

        public void setAction(String action) {
            Action = action;
        }

        public int getResourceId() {
            return ResourceId;
        }

        public void setResourceId(int resourceId) {
            ResourceId = resourceId;
        }

        @Override
        public String toString() {
            return Name;
        }

        /**
         * Выполнить комманду
         */
        public void exec(){
            Command.execute();
        }
    }

    public ShortCutModel.ShortCutItem[] getData() {
        ShortCutModel.ShortCutItem[]  data = new ShortCutItem[]{
                new ShortCutModel.ShortCutItem("Предыдущий трек","ru.forpda.example.an21utools.prev", R.drawable.media_skip_backward,new ICommand() {
                    @Override
                    public void execute() {
                        ModelFactory.getAutoRunModel().getMusicOperation().prevTrack();
                    }
                }),
                new ShortCutItem("Играть","ru.forpda.example.an21utools.play",R.drawable.media_playback_start,new ICommand() {
                    @Override
                    public void execute() {
                        ModelFactory.getAutoRunModel().getMusicOperation().play();
                    }
                }),
                new ShortCutItem("Пауза","ru.forpda.example.an21utools.pause",R.drawable.media_playback_pause, new ICommand() {
                    @Override
                    public void execute() {
                        ModelFactory.getAutoRunModel().getMusicOperation().pause();
                    }
                }),
                new ShortCutItem("Играть/Пауза","ru.forpda.example.an21utools.playpause",R.drawable.media_play_pause,new ICommand() {
                    @Override
                    public void execute() {
                        ModelFactory.getAutoRunModel().getMusicOperation().playPause();
                    }
                }),
                new ShortCutItem("Следующий трек","ru.forpda.example.an21utools.next",R.drawable.media_skip_forward, new ICommand() {
                    @Override
                    public void execute() {
                        ModelFactory.getAutoRunModel().getMusicOperation().nextTrack();
                    }
                }),
        };
        return data;
    }

}

