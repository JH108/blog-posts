import {TextInput} from 'react-native-paper';
import ParallaxScrollView from '@/components/ParallaxScrollView';
import {ThemedText} from '@/components/ThemedText';
import {FC, useCallback, useMemo, useState} from "react";
import Slider from "@react-native-community/slider";
import {Colors} from "@/constants/Colors";

export default function HomeScreen() {
    return (
        <ParallaxScrollView
            headerBackgroundColor={{light: '#A1CEDC', dark: Colors.dark.background}}
            headerImage={
                <></>
            }>
            <ThemedText type="subtitle">Using onValueChange</ThemedText>
            <PercentCompleteWithValue initialPercentComplete={0}/>
            <ThemedText type="subtitle">Using onSlidingComplete</ThemedText>
            <PercentCompleteWithCompleteSlide initialPercentComplete={0}/>

        </ParallaxScrollView>
    );
}

interface PercentCompleteWithCompleteSlideProps {
    initialPercentComplete?: number;
}

export const addPercentToText = (percent: number) => {
    if (percent || percent === 0) return `${percent}%`;
    return '';
};

const PercentCompleteWithValue: FC<PercentCompleteWithCompleteSlideProps> = ({initialPercentComplete}) => {
    const [percentComplete, setPercentComplete] = useState(
        initialPercentComplete || 0,
    );
    const onValueChange = useCallback((value: number) => {
        setPercentComplete(value);
    }, []);
    const percentCompleteText = useMemo(() => addPercentToText(percentComplete), [percentComplete]);

    return (
        <>
            <TextInput
                label={"Percent Complete"}
                value={percentCompleteText}
                disabled
                keyboardType="numeric"
                style={{
                    backgroundColor: '#dddddd',
                    borderRadius: 10,
                    flex: 1,
                }}
            />

            <Slider
                style={{
                    height: 40,
                    marginBottom: 40,
                    width: '100%',
                }}
                minimumValue={0}
                maximumValue={100}
                lowerLimit={initialPercentComplete}
                value={percentComplete}
                onValueChange={onValueChange}
                step={1}
                tapToSeek
                minimumTrackTintColor={'#204273'}
                maximumTrackTintColor={'#98A8BF'}
                thumbTintColor={'#204273'}
                testID="slider-percent-complete"
            />
        </>
    );
};

const PercentCompleteWithCompleteSlide: FC<PercentCompleteWithCompleteSlideProps> = ({initialPercentComplete}) => {
    const [percentComplete, setPercentComplete] = useState(
        initialPercentComplete || 0,
    );
    const onValueChange = useCallback((value: number) => {
        setPercentComplete(value);
    }, []);
    const percentCompleteText = useMemo(() => addPercentToText(percentComplete), [percentComplete]);

    return (
        <>
            <TextInput
                label={"Percent Complete"}
                value={percentCompleteText}
                disabled
                testID="input-percent-complete"
                keyboardType="numeric"
                style={{
                    backgroundColor: '#dddddd',
                    borderRadius: 10,
                    flex: 1,
                }}
            />

            <Slider
                style={{
                    height: 40,
                    marginBottom: 40,
                    width: '100%',
                }}
                minimumValue={0}
                maximumValue={100}
                lowerLimit={initialPercentComplete}
                value={percentComplete}
                onSlidingComplete={onValueChange}
                step={1}
                tapToSeek
                minimumTrackTintColor={'#204273'}
                maximumTrackTintColor={'#98A8BF'}
                thumbTintColor={'#204273'}
                testID="slider-percent-complete"
            />
        </>
    );
};
